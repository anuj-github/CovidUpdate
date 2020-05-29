package com.graduateguy.covid.holder

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.graduateguy.covid.R
import com.graduateguy.covid.databinding.CountryViewLayoutBinding
import com.graduateguy.covid.room.entity.CountryInfo
import com.graduateguy.covid.util.GlobalUtil

class CountryViewHolder private constructor(
    private val binding: CountryViewLayoutBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    fun setData(countryInfo: CountryInfo) {
        Log.d(TAG, "Country data  $countryInfo")
        binding.title.text = countryInfo.country
        binding.totalCount.text = GlobalUtil.getFormattedString(
            countryInfo.totalConfirmed,
            countryInfo.newConfirmed,
            binding.root.resources.getString(R.string.count)
        )
        binding.totalDeath.text = GlobalUtil.getFormattedString(
            countryInfo.totalDeaths,
            countryInfo.newDeaths,
            binding.root.resources.getString(R.string.count)
        )
        binding.totalRecovered.text = GlobalUtil.getFormattedString(
            countryInfo.totalRecovered,
            countryInfo.newRecovered,
            binding.root.resources.getString(R.string.count)
        )
    }

    companion object {
        private val TAG = CountryViewHolder::class.java.simpleName

        fun instantiate(parent: ViewGroup): CountryViewHolder {
            val binding = CountryViewLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CountryViewHolder(binding)
        }
    }
}
