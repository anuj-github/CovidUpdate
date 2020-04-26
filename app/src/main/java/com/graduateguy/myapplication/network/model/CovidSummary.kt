package com.graduateguy.myapplication.network.model

import com.google.gson.annotations.SerializedName
import com.graduateguy.myapplication.room.entity.CountryInfo
import com.graduateguy.myapplication.room.entity.GlobalSummary

data class CovidSummary(
    @SerializedName("Countries")
    val countries: List<CountryInfo> = listOf(),
    @SerializedName("Date")
    val date: String = "",
    @SerializedName("Global")
    val global: GlobalSummary
)