package com.graduateguy.covid.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.graduateguy.covid.network.api.Covid19Api
import com.graduateguy.covid.room.CovidDatabase
import com.graduateguy.covid.room.entity.CountryInfo
import com.graduateguy.covid.room.entity.GlobalSummary
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

interface ICovidRepository {

    fun getSummaryData():Flow<GlobalSummary>
    fun getMostAffectedCountry():Flow<List<CountryInfo>>
    fun loadGlobalSummary(onSuccess: ()-> Unit = {}, onFailure:(String)->Unit = {})
}

class CovidRepositoryImpl(
    private val network: Covid19Api,
    private val db: CovidDatabase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ICovidRepository, CoroutineScope {

    @WorkerThread
    override fun loadGlobalSummary(onSuccess: ()-> Unit, onFailure:(String)->Unit) {
        launch {
            try {
                val response = network.getCovidSummary().execute()
                if (response.isSuccessful) {
                    Log.d(TAG, "Response is successful")
                    response.body()?.apply {
                        global.let {
                            db.globalSummaryDao.delete()
                            db.globalSummaryDao.insert(it.copy(date = this.date))
                        }
                        db.countrydao.insertAll(countries)
                    }
                    onSuccess()
                } else {
                    onFailure("Api call failed to get response")
                    Log.d(TAG, "Response has failed")
                }
            } catch (ex: Exception) {
                onFailure(ex.localizedMessage)
                Log.d(TAG, "Exception occured $ex")
            }
        }
    }

    override fun getSummaryData(): Flow<GlobalSummary> = db.globalSummaryDao.getSummary()
    override fun getMostAffectedCountry(): Flow<List<CountryInfo>>  = db.countrydao.getSummaryMostAffected()

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + dispatcher

    companion object {
        private val TAG = CovidRepositoryImpl::class.java.simpleName
    }
}


