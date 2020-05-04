package com.graduateguy.covid.model

import androidx.annotation.ColorInt
import com.graduateguy.covid.R

data class CountModel(
    val count: Int,
    val msg: String,
    @ColorInt val  color: Int
)