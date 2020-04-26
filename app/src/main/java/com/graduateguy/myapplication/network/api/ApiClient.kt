package com.graduateguy.myapplication.network.api

import android.util.Log
import com.graduateguy.myapplication.network.Results
import com.graduateguy.myapplication.network.model.CovidSummary
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private lateinit var retrofit: Retrofit

    init {
        initializeRetrofit()
    }

    private fun initializeRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl("https://api.covid19api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getCovidApi(): Covid19Api {
        return retrofit.create(Covid19Api::class.java)
    }

     private fun <T> Call<T>.getResponse(result: (Results<T>) -> Unit) {
        enqueue(object: Callback<T> {
            override fun onFailure(call: Call<T>, error: Throwable) {
                Log.d(TAG, "Anuj on Failure")
                result(Results.Failure(call, error))
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                Log.d(TAG, "Anuj on Success")
                result(Results.Success(call, response))
            }
        })
    }
    fun getCovidSummaryData(result: (Results<CovidSummary>) -> Unit){
         getCovidApi().getCovidSummary().getResponse(result = result)
    }

    fun getCovid19Api()=getCovidApi()

    companion object{
        private const val TAG = "ApiCLient"
    }
}