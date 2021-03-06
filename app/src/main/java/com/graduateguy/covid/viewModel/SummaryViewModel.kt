package com.graduateguy.covid.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.graduateguy.covid.repository.ICovidRepository
import com.graduateguy.covid.room.entity.CountryInfo
import com.graduateguy.covid.room.entity.GlobalSummary

class SummaryViewModel(private val repository: ICovidRepository) : ViewModel() {

    private val _liveData = MutableLiveData<ResponseStatus>()
    val liveData: LiveData<ResponseStatus> = _liveData
    val testData: LiveData<Int> = MutableLiveData(1)

    fun refreshData() {
        repository.loadGlobalSummary({
            _liveData.postValue(ResponseStatus.Success())
        }, {
            _liveData.postValue(ResponseStatus.Failure(it))
        })
    }

    fun getSummaryLiveData(): LiveData<GlobalSummary> {
        return repository
            .getSummaryData()
            .asLiveData(viewModelScope.coroutineContext)
    }

    fun getCountryLiveData(): LiveData<List<CountryInfo>> {
        return repository
            .getAllCountryInfo()
            .asLiveData(viewModelScope.coroutineContext)
    }
}

sealed class ResponseStatus(open val status: String) {
    class Success() : ResponseStatus("")
    class Failure(override val status: String) : ResponseStatus(status)
}
