package com.graduateguy.covid.util

import com.github.mikephil.charting.utils.ColorTemplate

object GlobalUtil {

    public fun getColor():IntArray{
        var arr = IntArray(3)
        // Blue
        arr[0] = ColorTemplate.rgb("#ff33b5e5")
        // Green
        arr[1]= ColorTemplate.rgb("#ff99cc00")
        // Red
        arr[2]= ColorTemplate.rgb("#ffff4444")
        return arr;
    }

    fun getFormattedString(total: Int, new:Int, string: String): String {
        return String.format(string, total, new)
    }
}