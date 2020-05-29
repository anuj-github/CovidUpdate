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
    fun refreshData() {
        repository.loadGlobalSummary({
            _liveData.postValue(ResponseStatus.ResponseSuccess())
        }, {
            _liveData.postValue(ResponseStatus.ResponseFailure(it))
        })
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

sealed class ResponseStatus(open val status: String) {
    class ResponseSuccess() : ResponseStatus("")
    class ResponseFailure(override val status: String) : ResponseStatus(status)
}
