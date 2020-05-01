package com.graduateguy.covid.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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

        viewModel.getSummaryLiveData().observe(this, Observer {
            Log.d(TAG, "on data change")
            it?.let {summary->
                binding.apply {
                    totalCount.text =
                        viewModel.getFormattedString(
                            summary.totalConfirmed,
                            summary.newConfirmed,
                            getString(R.string.count)
                        )
                    totalDeath.text = viewModel.getFormattedString(
                        summary.totalDeaths, summary.newDeaths,
                        getString(R.string.count)
                    )
                    totalRecovered.text = viewModel.getFormattedString(
                        summary.totalRecovered, summary.newRecovered,
                        getString(R.string.count)
                    )
                }


            }
        })
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
        viewModel.refreshData()
    }

    companion object{
        private const val TAG = "LauncherActivity"
    }
}
