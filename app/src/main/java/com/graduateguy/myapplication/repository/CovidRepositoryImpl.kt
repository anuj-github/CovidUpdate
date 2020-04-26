package com.graduateguy.myapplication.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.graduateguy.myapplication.network.Results
import com.graduateguy.myapplication.network.api.ApiClient
import com.graduateguy.myapplication.network.model.CovidSummary

interface ICovidRepository<T>{

    fun getSummaryData():CovidSummary

}
class CovidRepositoryImpl<T> : ICovidRepository<T> {

    init {
        getCovidSummaryData()
    }

    private fun getCovidSummaryData() {
        ApiClient.getCovidSummaryData { result->
            when(result){
                is Results.Failure-> {
                    Log.d(TAG, " response is ${result.error}")
                }
                is Results.Success->{
                    val response = result.response.body()
                    Log.d(TAG, " response is $response")
                }
            }
        }
    }

    fun getSummaryData(): CovidSummary {
        return CovidSummary()
    }


    companion object{
        private const val TAG = "Covid19Repository"
    }
}