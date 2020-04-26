package com.graduateguy.myapplication.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.graduateguy.myapplication.network.api.ApiClient
import com.graduateguy.myapplication.repository.CovidRepositoryImpl
import com.graduateguy.myapplication.repository.ICovidRepository
import com.graduateguy.myapplication.room.CovidDatabase
import com.graduateguy.myapplication.room.entity.GlobalSummary

class LaunchViewModel( context: Application):AndroidViewModel(context) {

    private var summaryData = MutableLiveData<GlobalSummary>()
    private var summaryLiveData: LiveData<GlobalSummary> = summaryData

    private var repositoryImpl: ICovidRepository = CovidRepositoryImpl(
        ApiClient().getCovid19Api(), CovidDatabase.getCovidDatabase(context)
    )

    init {
        // refreshData()
    }

    private fun refreshData() {
        repositoryImpl.loadGlobalSummary()
    }

    fun getSummaryLiveData():LiveData<GlobalSummary> = repositoryImpl.getSummaryData()
}