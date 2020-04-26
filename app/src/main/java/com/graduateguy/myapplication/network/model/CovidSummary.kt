package com.graduateguy.myapplication.network.model

data class CovidSummary(
    val countries: List<Country> = listOf(),
    val date: String = "",
    val global: Global?= null
)