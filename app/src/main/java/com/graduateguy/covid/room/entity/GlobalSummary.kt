package com.graduateguy.covid.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class GlobalSummary(
    @PrimaryKey(autoGenerate = true)
    val id :Int,

    @field:Json(name = "NewConfirmed")
    val newConfirmed: Int = 1,

    @field:Json(name = "NewDeaths")
    val newDeaths: Int = 1,

    @field:Json(name = "NewRecovered")
    val newRecovered: Int = 1,

    @field:Json(name = "TotalConfirmed")
    val totalConfirmed: Int = 3,

    @field:Json(name = "TotalDeaths")
    val totalDeaths: Int = 1,

    @field:Json(name = "TotalRecovered")
    val totalRecovered: Int = 1,

    val date : String = ""

)