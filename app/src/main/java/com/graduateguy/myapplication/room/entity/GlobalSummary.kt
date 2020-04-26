package com.graduateguy.myapplication.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class GlobalSummary(
    @PrimaryKey(autoGenerate = true)
    val id :Int,
    @SerializedName("NewConfirmed")
    val newConfirmed: Int,
    @SerializedName("NewDeaths")
    val newDeaths: Int,
    @SerializedName("NewRecovered")
    val newRecovered: Int,
    @SerializedName("TotalConfirmed")
    val totalConfirmed: Int,
    @SerializedName("TotalDeaths")
    val totalDeaths: Int,
    @SerializedName("TotalRecovered")
    val totalRecovered: Int
)