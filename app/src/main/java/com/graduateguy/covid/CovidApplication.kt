package com.graduateguy.covid

import android.app.Application
import android.content.Context

class CovidApplication: Application() {

    private lateinit var application: CovidApplication

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        application = this
    }

    fun getApplication(): CovidApplication {
        return this
    }
}