package com.graduateguy.myapplication.network.model

import com.graduateguy.myapplication.room.entity.CountryInfo
import com.graduateguy.myapplication.room.entity.GlobalSummary
import com.squareup.moshi.Json

data class CovidSummary(
    @field:Json(name = "Countries")
    val countries: List<CountryInfo> = listOf(),
    @field:Json(name = "Date")
    val date: String = "",
    @field:Json(name = "Global")
    val global: GlobalSummary
)