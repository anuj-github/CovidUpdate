package com.graduateguy.covid.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.graduateguy.covid.holder.CountViewHolder
import com.graduateguy.covid.model.CountModel

class CountAdapter : RecyclerView.Adapter<CountViewHolder>() {

    private val countRecord = arrayListOf<CountModel>()

    fun setData(countList: List<CountModel>) {
        countRecord.clear()
        countRecord.addAll(countList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountViewHolder {
        return CountViewHolder.instantiate(parent)
    }

    override fun getItemCount(): Int = countRecord.size

    override fun onBindViewHolder(holder: CountViewHolder, position: Int) {
        (holder as? CountViewHolder)?.setData(countRecord[position])
    }
}