package com.graduateguy.covid.search.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.graduateguy.covid.R
import com.graduateguy.covid.adapter.CountryListAdapter
import com.graduateguy.covid.databinding.SearchLayoutBinding
import com.graduateguy.covid.search.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var countryListAdapter: CountryListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: SearchLayoutBinding
    private lateinit var searchView: SearchView
    private val searchViewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SearchLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()
        loadAllData()
    }

    private fun initAdapter() {
        recyclerView = binding.recyclerView
        countryListAdapter = CountryListAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter = countryListAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)

        // Get the SearchView and set the searchable configuration
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.apply {
            // Assumes current activity is the searchable activity
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            isIconifiedByDefault = false // Do not iconify the widget; expand it by default
            setOnQueryTextListener(this@SearchActivity)
        }

        return true
    }

    private fun loadAllData() {
        Log.d(TAG, "load All Data")
        searchViewModel.searchCountry().observe(this, Observer {
            countryListAdapter.setData(it)
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        Log.i(TAG, "Text changed")
        countryListAdapter.filter.filter(query)
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        countryListAdapter.filter.filter(query)
        return true
    }

    companion object {
        private val TAG = SearchActivity::class.java.simpleName
    }
}
