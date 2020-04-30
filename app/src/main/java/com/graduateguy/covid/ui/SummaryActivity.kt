package com.graduateguy.covid.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.graduateguy.covid.R
import com.graduateguy.covid.databinding.SummaryLayoutBinding
import com.graduateguy.covid.viewModel.LaunchViewModel

class SummaryActivity : AppCompatActivity() {

    private lateinit var binding: SummaryLayoutBinding
    private lateinit var viewModel: LaunchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SummaryLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(LaunchViewModel::class.java)
        // viewModel.refreshData()

        viewModel.getSummaryLiveData().observe(this, Observer {
            Log.d(TAG, "Anuj on data change")
            it?.let {summary->
                Log.d(TAG, "Anuj Summary is $summary")
                binding.apply {
                    totalCount.text =
                        viewModel.getString(
                            summary.totalConfirmed,
                            summary.newConfirmed,
                            getString(R.string.count)
                        )
                    totalDeath.text = viewModel.getString(
                        summary.totalDeaths, summary.newDeaths,
                        getString(R.string.count)
                    )
                    totalRecovered.text = viewModel.getString(
                        summary.totalRecovered, summary.newRecovered,
                        getString(R.string.count)
                    )
                }


            }
        })
    }

    companion object{
        private const val TAG = "LauncherActivity"
    }
}
