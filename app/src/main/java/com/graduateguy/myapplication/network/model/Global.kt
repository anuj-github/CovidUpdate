package com.graduateguy.myapplication.network.model

data class Global(
    val newConfirmed: Int,
    val newDeaths: Int,
    val newRecovered: Int,
    val totalConfirmed: Int,
    val totalDeaths: Int,
    val totalRecovered: Int
)