package com.graduateguy.covid.ui.fragment

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.graduateguy.covid.adapter.CountryListAdapter

class CountryInfoFragment:Fragment() {

    private lateinit var countryListAdapter: CountryListAdapter
    private lateinit var recyclerView : RecyclerView

    private fun initAdapter() {
        // recyclerView = binding.recylerView
        countryListAdapter = CountryListAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = countryListAdapter
        }

    }
}