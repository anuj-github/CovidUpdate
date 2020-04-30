package com.graduateguy.covid.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class CountryInfo(

    @PrimaryKey
    @field:Json(name = "Country")
    val country: String,

    @field:Json(name = "CountryCode")
    val countryCode: String,

    @field:Json(name = "Date")
    val date: String,

    @field:Json(name = "NewConfirmed")
    val newConfirmed: Int,

    @field:Json(name = "NewDeaths")
    val newDeaths: Int,

    @field:Json(name = "NewRecovered")
    val newRecovered: Int,

    @field:Json(name = "Slug")
    val slug: String,

    @field:Json(name = "TotalConfirmed")
    val totalConfirmed: Int,

    @field:Json(name = "TotalDeaths")
    val totalDeaths: Int,

    @field:Json(name = "TotalRecovered")
    val totalRecovered: Int
)