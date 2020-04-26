package com.graduateguy.myapplication.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.graduateguy.myapplication.databinding.ActivityMainBinding
import com.graduateguy.myapplication.viewModel.LaunchViewModel

class LauncherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: LaunchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(LaunchViewModel::class.java)
        // viewModel.refreshData()

        viewModel.getSummaryLiveData().observe(this, Observer {
            Log.d(TAG, "Anuj Live data change")
            it?.let {
                Log.d(TAG, "Anuj Summary is $it")
                binding.totalCount.text = it.totalConfirmed.toString()
            }
        })
    }

    companion object{
        private const val TAG = "LauncherActivity"
    }
}
