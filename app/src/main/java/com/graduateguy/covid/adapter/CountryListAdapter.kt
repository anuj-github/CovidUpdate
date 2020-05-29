package com.graduateguy.covid.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.graduateguy.covid.holder.CountryViewHolder
import com.graduateguy.covid.room.entity.CountryInfo

class CountryListAdapter : RecyclerView.Adapter<CountryViewHolder>() {

    private var countryInfoList = arrayListOf<CountryInfo>()

    fun setData(countryList: List<CountryInfo> = arrayListOf()) {
        countryInfoList.clear()
        countryInfoList.addAll(countryList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder.instantiate(parent)
    }

    override fun getItemCount(): Int = countryInfoList.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        (holder as? CountryViewHolder)?.setData(countryInfoList[position])
    }

    companion object {
        private const val TAG = "CountryListAdapter"
    }
}
