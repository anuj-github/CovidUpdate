package com.graduateguy.covid.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.graduateguy.covid.room.entity.CountryInfo
import com.graduateguy.covid.search.repo.ISearchRepository

class SearchViewModel(private val repository: ISearchRepository) : ViewModel() {

    fun searchCountry(): LiveData<List<CountryInfo>> {
        return repository.getCountry().asLiveData(viewModelScope.coroutineContext)
    }
}
