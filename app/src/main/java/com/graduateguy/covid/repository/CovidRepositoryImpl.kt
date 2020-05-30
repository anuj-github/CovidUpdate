package com.graduateguy.covid.repository

import android.util.Log
import com.graduateguy.covid.network.api.Covid19Api
import com.graduateguy.covid.room.dao.CountryDao
import com.graduateguy.covid.room.dao.GlobalSummaryDao
import com.graduateguy.covid.room.entity.CountryInfo
import com.graduateguy.covid.room.entity.GlobalSummary
import java.lang.Exception
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

interface ICovidRepository {

    fun getSummaryData(): Flow<GlobalSummary>
    fun getAllCountryInfo(): Flow<List<CountryInfo>>
    fun loadGlobalSummary(onSuccess: () -> Unit = {}, onFailure: (String) -> Unit = {})
}

class CovidRepositoryImpl(
    private val network: Covid19Api,
    private val countryDao: CountryDao,
    private val summaryDao: GlobalSummaryDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ICovidRepository, CoroutineScope {

    override fun loadGlobalSummary(onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        launch {
            try {
                val response = network.getCovidSummary().execute()
                if (response.isSuccessful) {
                    Log.d(TAG, "Response is successful")
                    response.body()?.apply {
                        global.let {
                            summaryDao.delete()
                            summaryDao.insert(it.copy(date = this.date))
                        }
                        countryDao.insertAll(countries)
                    }
                    onSuccess()
                } else {
                    onFailure("Api call failed to get response")
                    Log.d(TAG, "Response has failed")
                }
            } catch (ex: Exception) {
                onFailure(ex.localizedMessage ?: "Failed")
                Log.d(TAG, "Exception occured $ex")
            }
        }
    }

    override fun getSummaryData(): Flow<GlobalSummary> = summaryDao.getSummary()
    override fun getAllCountryInfo(): Flow<List<CountryInfo>> = countryDao.getAllCountry()

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + dispatcher

    companion object {
        private val TAG = CovidRepositoryImpl::class.java.simpleName
    }
}
