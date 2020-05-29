package com.graduateguy.covid.network.api

import com.graduateguy.covid.network.model.CovidSummary
import retrofit2.Call
import retrofit2.http.GET

interface Covid19Api {

    @GET("summary")
    fun getCovidSummary(): Call<CovidSummary>
}
