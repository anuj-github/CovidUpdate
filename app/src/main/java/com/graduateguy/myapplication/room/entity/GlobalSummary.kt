package com.graduateguy.myapplication.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class GlobalSummary(
    @PrimaryKey(autoGenerate = true)
    val id :Int,

    @field:Json(name = "NewConfirmed")
    val newConfirmed: Int,

    @field:Json(name = "NewDeaths")
    val newDeaths: Int,

    @field:Json(name = "NewRecovered")
    val newRecovered: Int,

    @field:Json(name = "TotalConfirmed")
    val totalConfirmed: Int,

    @field:Json(name = "TotalDeaths")
    val totalDeaths: Int,

    @field:Json(name = "TotalRecovered")
    val totalRecovered: Int
)