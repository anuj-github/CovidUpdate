package com.graduateguy.covid.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.graduateguy.covid.R
import com.graduateguy.covid.databinding.CountCardBinding
import com.graduateguy.covid.databinding.SummaryFragmentBinding
import com.graduateguy.covid.room.entity.GlobalSummary
import com.graduateguy.covid.util.GlobalUtil
import com.graduateguy.covid.viewModel.SummaryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SummaryFragment:Fragment() {

    private lateinit var pieChart : PieChart
    private val pieData : PieData = PieData()
    private lateinit var pieDataSet : PieDataSet
    private var pieEntries = mutableListOf<PieEntry>()
    private lateinit var binding : SummaryFragmentBinding
    private val summaryViewModel : SummaryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SummaryFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pieChart = binding.pieChart
        pieChart.description = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeGlobalSummary()
    }

    private fun observeGlobalSummary() {
        Log.d(TAG, "observeGlobalSummary")
        summaryViewModel.getSummaryLiveData().observe(this.viewLifecycleOwner, Observer {
            Log.d(TAG, "on observeGlobalSummary data change")
            it?.let {
                    updateEntries(it)
                    updateCountCard(it)
            }
        })
    }

    private fun updateCountCard(summary: GlobalSummary) {
        val flexBoxLayout = binding.flexLayout
        flexBoxLayout.removeAllViews()
        val inflater = LayoutInflater.from(flexBoxLayout.context)
        val totalView = CountCardBinding.inflate(inflater, flexBoxLayout, false)
        totalView.casemsg.text = getString(R.string.total_case)
        totalView.casecount.text = summary.totalConfirmed.toString()
        flexBoxLayout.addView(totalView.root)

        val newCaseView = CountCardBinding.inflate(inflater, flexBoxLayout, false)
        newCaseView.casemsg.text = getString(R.string.new_case)
        newCaseView.casecount.text = summary.newConfirmed.toString()
        flexBoxLayout.addView(newCaseView.root)

        val newsDeath = CountCardBinding.inflate(inflater, flexBoxLayout, false)
        newsDeath.casemsg.text = getString(R.string.new_death)
        newsDeath.casecount.text = summary.newDeaths.toString()
        flexBoxLayout.addView(newsDeath.root)

        val newsRecovered = CountCardBinding.inflate(inflater, flexBoxLayout, false)
        newsRecovered.casemsg.text = getString(R.string.new_recovered)
        newsRecovered.casecount.text = summary.newRecovered.toString()
        flexBoxLayout.addView(newsRecovered.root)

    }

    private fun updateEntries(summary: GlobalSummary) {
        Log.d(TAG, "update Entries")
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
        pieData.clearValues()
        pieData.addDataSet(pieDataSet)
        pieData.dataSet = pieDataSet
        pieData.notifyDataChanged()
        pieChart.data = pieData
        val arr = GlobalUtil.getColor()
        pieDataSet.setColors(arr[0], arr[1], arr[2])
        pieDataSet.sliceSpace = 1f
        pieDataSet.valueTextColor = Color.WHITE
        pieDataSet.valueTextSize = 10f
        pieChart.centerText = getString(R.string.covid_summary)
        pieChart.invalidate()

    }

    companion object{
        private const val TAG = "SummaryFragment"
    }
}
