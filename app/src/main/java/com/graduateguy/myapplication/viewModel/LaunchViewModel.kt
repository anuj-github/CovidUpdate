package com.graduateguy.myapplication.viewModel

import androidx.lifecycle.ViewModel
import com.graduateguy.myapplication.network.model.CovidSummary
import com.graduateguy.myapplication.repository.CovidRepositoryImpl
import com.graduateguy.myapplication.repository.ICovidRepository

class LaunchViewModel:ViewModel() {

    var repositoryImpl: ICovidRepository<CovidSummary> = CovidRepositoryImpl<CovidSummary>()

    fun refreshData() {
        // repo.getLiveData().o
    }
}