package com.graduateguy.covid.network.model

import com.graduateguy.covid.room.entity.CountryInfo
import com.graduateguy.covid.room.entity.GlobalSummary
import com.squareup.moshi.Json

data class CovidSummary(
    @field:Json(name = "Countries")
    val countries: List<CountryInfo> = listOf(),
    @field:Json(name = "Date")
    val date: String = "",
    @field:Json(name = "Global")
    val global: GlobalSummary
)
