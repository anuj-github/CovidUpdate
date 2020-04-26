package com.graduateguy.myapplication.network.model

data class Country(
    val country: String,
    val countryCode: String,
    val date: String,
    val newConfirmed: Int,
    val newDeaths: Int,
    val newRecovered: Int,
    val slug: String,
    val totalConfirmed: Int,
    val totalDeaths: Int,
    val totalRecovered: Int
)