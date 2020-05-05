package com.graduateguy.covid.ui.fragment

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.graduateguy.covid.R
import com.graduateguy.covid.adapter.CountAdapter
import com.graduateguy.covid.databinding.SummaryFragmentBinding
import com.graduateguy.covid.model.CountModel
import com.graduateguy.covid.room.entity.GlobalSummary
import com.graduateguy.covid.util.GlobalUtil
import com.graduateguy.covid.viewModel.SummaryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SummaryFragment:Fragment() {

    private lateinit var countAdapter: CountAdapter
    private lateinit var pieChart : PieChart
    private val pieData : PieData = PieData()
    private lateinit var pieDataSet : PieDataSet
    private var pieEntries = mutableListOf<PieEntry>()
    private lateinit var binding : SummaryFragmentBinding
    private lateinit var recyclerView : RecyclerView
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
        initAdapter()
    }

    private fun initAdapter() {
        recyclerView = binding.countRecylerView
        countAdapter = CountAdapter()
        recyclerView.apply {
            layoutManager = GridLayoutManager(activity, 3)
            adapter = countAdapter
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeGlobalSummary()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun observeGlobalSummary() {
        Log.d(TAG, "observeGlobalSummary")
        summaryViewModel.getSummaryLiveData().observe(this.viewLifecycleOwner, Observer {
            Log.d(TAG, "on observeGlobalSummary data change")
            it?.let {
                    updateEntries(it)
                    updateCountAdapter(it)
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun updateCountAdapter(it: GlobalSummary) {
        val list: List<CountModel> = it.createCountModelList(requireContext())
        countAdapter.setData(list)
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
    }

    companion object{
        private const val TAG = "SummaryFragment"
    }
}

@RequiresApi(Build.VERSION_CODES.M)
private fun GlobalSummary.createCountModelList(context:Context): List<CountModel> {
    val list = arrayListOf<CountModel>()
    list.add(CountModel(this.totalConfirmed, "Total",context.getColor(R.color.black) ))
    list.add(CountModel(this.newConfirmed, "New Cases", context.getColor(R.color.colorPrimary)))
    list.add(CountModel(this.newRecovered, "New Recovered", context.getColor(R.color.colorAccent)))
    list.add(CountModel(this.newDeaths, "New Death", context.getColor(R.color.colorPrimaryDark)))
    return list
}
