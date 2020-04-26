package com.graduateguy.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        viewModel.refreshData()
    }
}
