package com.graduateguy.covid.search.ui

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.graduateguy.covid.adapter.CountryListAdapter
import com.graduateguy.covid.databinding.SearchLayoutBinding
import com.graduateguy.covid.search.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {

    private lateinit var countryListAdapter: CountryListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: SearchLayoutBinding
    private val searchViewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = SearchLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()
        handleIntent(intent)

    }

    private fun initAdapter() {
        recyclerView = binding.recyclerView
        countryListAdapter = CountryListAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter = countryListAdapter
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {

        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            searchViewModel.searchCountry(query).observe(this, Observer {
                countryListAdapter.setData(it)
            })
        }
    }
}
