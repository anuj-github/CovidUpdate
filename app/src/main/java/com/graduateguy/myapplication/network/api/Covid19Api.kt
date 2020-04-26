package com.graduateguy.myapplication.network.api

import com.graduateguy.myapplication.network.model.CovidSummary
import retrofit2.Call
import retrofit2.http.GET

interface Covid19Api {

    @GET("summary")
    fun getCovidSummary(): Call<CovidSummary>
}