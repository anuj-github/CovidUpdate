package com.graduateguy.covid.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.graduateguy.covid.R
import com.graduateguy.covid.model.CountModel
import kotlinx.android.synthetic.main.count_card.view.*

class CountViewHolder private constructor (itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val view: View = itemView

    fun setData(model: CountModel) {
        view.casemsg.text = model.msg
        view.casemsg.setTextColor(model.color)
        view.casecount.text = model.count.toString()
        view.casecount.setTextColor(model.color)
    }

    companion object {
        fun instantiate(parent: ViewGroup): CountViewHolder {
            return CountViewHolder(
                createView(
                    parent,
                    R.layout.count_card
                )
            )
        }

        private fun createView(parent: ViewGroup, layoutId: Int): View {
            return LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        }
    }
}
