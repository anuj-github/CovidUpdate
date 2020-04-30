package com.graduateguy.covid.repository

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.graduateguy.covid.network.api.Covid19Api
import com.graduateguy.covid.room.CovidDatabase
import com.graduateguy.covid.room.entity.GlobalSummary
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

interface ICovidRepository {

    fun getSummaryData():LiveData<GlobalSummary>
    fun loadGlobalSummary()
}

class CovidRepositoryImpl(
    private val network: Covid19Api,
    private val db: CovidDatabase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ICovidRepository, CoroutineScope {

    init {
        loadGlobalSummary()
    }

    @WorkerThread
    override fun loadGlobalSummary() {
        launch {
            try {
                val response = network.getCovidSummary().execute()
                if (response.isSuccessful) {
                    Log.d(TAG, "Response is successfull")
                    response.body()?.apply {
                        global.let {
                            db.globalSummaryDao.delete()
                            db.globalSummaryDao.insert(it)
                        }
                        countries.forEach {
                            db.countrydao.update(it)
                        }
                    }
                } else {
                    Log.d(TAG, "Response has failed")
                }
            } catch (ex: Exception) {
                Log.d(TAG, "Exception occured $ex")
            }
        }
    }

    override fun getSummaryData():LiveData<GlobalSummary> {
        return db.globalSummaryDao.getSummary()
    }

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + dispatcher

    companion object {
        private const val TAG = "Covid19Repository"
    }
}


