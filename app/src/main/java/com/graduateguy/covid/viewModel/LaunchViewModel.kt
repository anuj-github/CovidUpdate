package com.graduateguy.covid.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.graduateguy.covid.network.api.RetrofitClient
import com.graduateguy.covid.repository.CovidRepositoryImpl
import com.graduateguy.covid.repository.ICovidRepository
import com.graduateguy.covid.room.CovidDatabase
import com.graduateguy.covid.room.entity.CountryInfo
import com.graduateguy.covid.room.entity.GlobalSummary

class LaunchViewModel(context: Application):AndroidViewModel(context) {

    private var repositoryImpl: ICovidRepository = CovidRepositoryImpl(
        RetrofitClient().getCovid19Api(), CovidDatabase.getCovidDatabase(context)
    )

    fun refreshData() {
        repositoryImpl.loadGlobalSummary()
    }

    fun getSummaryLiveData(): LiveData<GlobalSummary> {
        return repositoryImpl
            .getSummaryData()
            .asLiveData(viewModelScope.coroutineContext)
    }

    fun getCountryLiveData(): LiveData<List<CountryInfo>> {
        return repositoryImpl
            .getMostAffectedCountry()
            .asLiveData(viewModelScope.coroutineContext)
    }
}