package com.graduateguy.covid.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.graduateguy.covid.adapter.CountryListAdapter
import com.graduateguy.covid.databinding.CountryFragmentLayoutBinding
import com.graduateguy.covid.viewModel.SummaryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CountryInfoFragment:Fragment() {

    private lateinit var countryListAdapter: CountryListAdapter
    private lateinit var recyclerView : RecyclerView
    private lateinit var binding : CountryFragmentLayoutBinding
    private val summaryViewModel : SummaryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CountryFragmentLayoutBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        observeCountryData()
    }
    private fun initAdapter() {
        recyclerView = binding.recyclerView
        countryListAdapter = CountryListAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = countryListAdapter
        }

    }

    private fun observeCountryData() {
        Log.d(TAG, "observe Country Info")
        summaryViewModel.getCountryLiveData().observe(this.viewLifecycleOwner, Observer {
            Log.d(TAG, "on Country info data change")
            it?.let {
               countryListAdapter.setData(it)
            }
        })
    }

    companion object {
        private const val TAG = ""
    }
}