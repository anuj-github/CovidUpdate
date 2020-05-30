package com.graduateguy.covid

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.graduateguy.covid.network.api.Covid19Api
import com.graduateguy.covid.repository.CovidRepositoryImpl
import com.graduateguy.covid.repository.ICovidRepository
import com.graduateguy.covid.room.CovidDatabase
import com.graduateguy.covid.search.repo.ISearchRepository
import com.graduateguy.covid.search.repo.SearchRepositoryImpl
import com.graduateguy.covid.search.viewmodel.SearchViewModel
import com.graduateguy.covid.viewModel.SummaryViewModel
import com.graduateguy.covid.worker.UpdateDataWorker
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class CovidApplication : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CovidApplication)
            modules(applicationModule)
        }
        init()
    }

    private fun init() {
        // Initialize basic app start data
        UpdateDataWorker.scheduleWork(this)
    }

    private val applicationModule = module {
        single {
            Room.databaseBuilder(
                this@CovidApplication,
                CovidDatabase::class.java,
                CovidDatabase.DB_NAME
            )
                .build()
        }
        single {
            Retrofit.Builder()
                .baseUrl("https://api.covid19api.com/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build().create(Covid19Api::class.java)
        }
        single<ICovidRepository> {
            CovidRepositoryImpl(get(), get())
        }

        single<ISearchRepository> {SearchRepositoryImpl(get())  }

        viewModel { SummaryViewModel(get()) }
        viewModel { SearchViewModel(get()) }
    }
}
