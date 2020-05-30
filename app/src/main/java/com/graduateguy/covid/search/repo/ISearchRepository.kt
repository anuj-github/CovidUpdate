package com.graduateguy.covid.search.repo

import com.graduateguy.covid.room.CovidDatabase
import com.graduateguy.covid.room.entity.CountryInfo
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow

interface ISearchRepository {

    fun getCountry(searchKey: String = ""): Flow<List<CountryInfo>>
}

class SearchRepositoryImpl(
    private val db: CovidDatabase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ISearchRepository, CoroutineScope {

    override fun getCountry(searchKey: String): Flow<List<CountryInfo>> {
        return db.countrydao.getAllCountry()
    }

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + dispatcher
}
