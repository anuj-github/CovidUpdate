package com.graduateguy.covid.search.viewmodel

import androidx.lifecycle.*
import com.graduateguy.covid.room.entity.CountryInfo
import com.graduateguy.covid.search.repo.ISearchRepository

class SearchViewModel(private val repository: ISearchRepository) : ViewModel() {

    fun searchCountry(searchKey:String):LiveData<List<CountryInfo>> {
        return repository.getCountry(searchKey).asLiveData(viewModelScope.coroutineContext)
    }
}