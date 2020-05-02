package com.graduateguy.covid.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.graduateguy.covid.R
import com.graduateguy.covid.adapter.CountryListAdapter
import com.graduateguy.covid.databinding.SummaryGraphBinding
import com.graduateguy.covid.room.entity.GlobalSummary
import com.graduateguy.covid.util.GlobalUtil
import com.graduateguy.covid.viewModel.SummaryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SummaryActivity : AppCompatActivity() {

    private lateinit var binding : SummaryGraphBinding
    private val summaryViewModel : SummaryViewModel by viewModel()
    private lateinit var pieChart : PieChart
    private lateinit var pieData : PieData
    private lateinit var pieDataSet : PieDataSet
    private var pieEntries = mutableListOf<PieEntry>()
    private lateinit var recyclerView : RecyclerView
    private lateinit var countryListAdapter : CountryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SummaryGraphBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pieChart = binding.pieChart
        pieChart.description = null
        initAdapter()
        observeGlobalSummary()
        observeCountryData()
    }

    private fun observeCountryData() {
        Log.d(TAG, "Anuj observeCountryData")
        summaryViewModel.getCountryLiveData().observe(this, Observer {
            Log.d(TAG, "Anuj data change $it")
            if(it.isNullOrEmpty()){
                binding.mostAffected.visibility = GONE
            }
            it.let {
               countryListAdapter.setData(it)
            }
        })
    }

    private fun observeGlobalSummary() {
        Log.d(TAG, "Anuj observeGlobalSummary")
        summaryViewModel.getSummaryLiveData().observe(this, Observer {
            Log.d(TAG, "on observeGlobalSummary data change")
            it?.let {
                binding.apply {
                    updateChart(it)
                }

            }
        })
    }

    private fun initAdapter() {
        recyclerView = binding.recylerView
        countryListAdapter = CountryListAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SummaryActivity)
            adapter = countryListAdapter
        }

    }

    private fun updateEntries(summary: GlobalSummary) {
        val death = summary.totalDeaths
        val recovered = summary.totalRecovered
        val active = summary.totalConfirmed - death - recovered
        pieEntries = ArrayList()
        pieEntries.add(PieEntry(active.toFloat(), 0))
        pieEntries.add(PieEntry(recovered.toFloat(), 1))
        pieEntries.add(PieEntry(death.toFloat(), 2))

        pieEntries[0].label = getString(R.string.active)
        pieEntries[1].label = getString(R.string.recovered)
        pieEntries[2].label = getString(R.string.death)

        pieDataSet = PieDataSet(pieEntries, "")
        pieData = PieData(pieDataSet)
        pieChart.data = pieData
        val arr = GlobalUtil.getColor()
        pieDataSet.setColors(arr[0], arr[1], arr[2])
        pieDataSet.sliceSpace = 1f
        pieDataSet.valueTextColor = Color.WHITE
        pieDataSet.valueTextSize = 10f
    }

    private fun updateChart(summary: GlobalSummary){
        val death = ((summary.totalDeaths*100)/summary.totalConfirmed)
        val recovered = ((summary.totalRecovered*100)/summary.totalConfirmed)
        binding.apply {
           updateEntries(summary)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.summary_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.refresh -> refreshData()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun refreshData(){
        summaryViewModel.refreshData()
    }



    companion object{
        private const val TAG = "LauncherActivity"
    }
}
