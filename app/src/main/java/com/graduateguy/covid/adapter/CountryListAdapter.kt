package com.graduateguy.covid.adapter

import android.util.Log
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.graduateguy.covid.holder.CountryViewHolder
import com.graduateguy.covid.room.entity.CountryInfo

class CountryListAdapter : RecyclerView.Adapter<CountryViewHolder>(), Filterable {

    private var countryInfoList = arrayListOf<CountryInfo>()
    private var countryInfoFiltered = countryInfoList

    fun setData(countryList: List<CountryInfo> = arrayListOf()) {
        countryInfoList.clear()
        countryInfoList.addAll(countryList)
        countryInfoFiltered = countryInfoList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder.instantiate(parent)
    }

    override fun getItemCount(): Int = countryInfoFiltered.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        (holder as? CountryViewHolder)?.setData(countryInfoFiltered[position])
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                Log.d(TAG, "Search Query $constraint")
                if (constraint.isNullOrEmpty()) countryInfoFiltered = countryInfoList
                countryInfoFiltered = countryInfoList.filter { countryInfo ->
                    countryInfo.country.contains(constraint ?: "", true)
                } as ArrayList<CountryInfo>

                val filteredResult = FilterResults()
                filteredResult.values = countryInfoFiltered
                return filteredResult
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                countryInfoFiltered = (results?.values ?: countryInfoList) as ArrayList<CountryInfo>
                notifyDataSetChanged()
            }
        }
    }

    companion object {
        private val TAG = CountryListAdapter::class.java.simpleName
    }
}
