package com.graduateguy.covid.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.graduateguy.covid.R
import com.graduateguy.covid.room.entity.CountryInfo
import com.graduateguy.covid.util.GlobalUtil
import kotlinx.android.synthetic.main.country_layout.view.*

class CountryViewHolder private constructor( itemView: View): RecyclerView.ViewHolder(itemView) {

    private val view = itemView

    fun setData(countryInfo: CountryInfo) {
        view.title.text = countryInfo.country
        view.totalCount.text = GlobalUtil.getFormattedString(
            countryInfo.totalConfirmed,
            countryInfo.newConfirmed,
            view.totalCount.text.toString()
        )
        view.totalDeath.text = GlobalUtil.getFormattedString(
            countryInfo.totalDeaths,
            countryInfo.newDeaths,
            view.totalDeath.text.toString()
        )
        view.totalRecovered.text = GlobalUtil.getFormattedString(
            countryInfo.totalRecovered,
            countryInfo.newRecovered,
            view.totalRecovered.text.toString()
        )
    }

    companion object{

        fun instantiate(parent: ViewGroup): CountryViewHolder {
            return CountryViewHolder(
                createView(
                    parent,
                    R.layout.country_layout
                )
            )
        }

        private fun createView(parent: ViewGroup, layoutId: Int): View {
            return LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        }
    }
}
