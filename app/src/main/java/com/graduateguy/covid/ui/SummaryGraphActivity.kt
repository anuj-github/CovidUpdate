package com.graduateguy.covid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.utils.ColorTemplate
import com.graduateguy.covid.databinding.SummaryGraphBinding
import com.graduateguy.covid.room.entity.GlobalSummary

class SummaryGraphActivity:AppCompatActivity() {

    private lateinit var binding: SummaryGraphBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SummaryGraphBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun updateChart(summary:GlobalSummary){

        val death:Double = (summary.totalConfirmed/summary.totalDeaths).toDouble()
        val recovered = (summary.totalRecovered/summary.totalDeaths).toDouble()
        binding.apply {
        }
    }

    fun getColor():IntArray{
        var arr = IntArray(3)
        arr[0] = ColorTemplate.rgb("#ff33b5e5")
        arr[1]= ColorTemplate.rgb("#ff99cc00")
        arr[2]= ColorTemplate.rgb("#ffff4444")
        return arr;
    }
}