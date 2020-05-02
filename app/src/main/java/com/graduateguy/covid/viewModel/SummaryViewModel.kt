package com.graduateguy.covid.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.graduateguy.covid.repository.ICovidRepository
import com.graduateguy.covid.room.entity.CountryInfo
import com.graduateguy.covid.room.entity.GlobalSummary

class SummaryViewModel(private val repository: ICovidRepository):ViewModel() {

    fun refreshData() {
        repository.loadGlobalSummary()
    }

    fun getSummaryLiveData(): LiveData<GlobalSummary> {
        return repository
            .getSummaryData()
            .asLiveData(viewModelScope.coroutineContext)
    }

    fun getCountryLiveData(): LiveData<List<CountryInfo>> {
        return repository
            .getMostAffectedCountry()
            .asLiveData(viewModelScope.coroutineContext)
    }
}