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
        Toast.makeText(this, "Show", Toast.LENGTH_LONG).show()
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
        Log.i(TAG, "Init adaper")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the options menu from XML
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
        Log.d(TAG, "search")
        searchViewModel.searchCountry().observe(this, Observer {
            Log.d(TAG, "Received result $it")
            countryListAdapter.setData(it)
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        Log.i(TAG, "Text changed")
        countryListAdapter.filter.filter(query ?: "")
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    companion object {
        private val TAG = SearchActivity::class.java.simpleName
    }
}
