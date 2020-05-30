package com.graduateguy.covid

import android.app.Application
import android.content.Context
import com.graduateguy.covid.di.databaseModule
import com.graduateguy.covid.di.networkModule
import com.graduateguy.covid.di.repoModule
import com.graduateguy.covid.di.viewModelModule
import com.graduateguy.covid.worker.UpdateDataWorker
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CovidApplication : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CovidApplication)
            modules(databaseModule(this@CovidApplication), networkModule, repoModule, viewModelModule)
        }
        init()
    }

    private fun init() {
        // Initialize basic app start data
        UpdateDataWorker.scheduleWork(this)
    }
}
