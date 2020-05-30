package com.graduateguy.covid.di

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
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://api.covid19api.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    single { getCovidApi(get()) }
}

private fun getCovidApi(retrofit: Retrofit) = retrofit.create(Covid19Api::class.java)

fun databaseModule(context: Context) = module {
    single {
        Room.databaseBuilder(
            context,
            CovidDatabase::class.java,
            CovidDatabase.DB_NAME
        )
            .build()
    }

    single { getCountryDao(get()) }
    single { getSummaryDao(get()) }
}

private fun getCountryDao(db: CovidDatabase) = db.countrydao
private fun getSummaryDao(db: CovidDatabase) = db.globalSummaryDao

val repoModule = module {
    single<ICovidRepository> {
        CovidRepositoryImpl(get(), get(), get())
    }

    single<ISearchRepository> { SearchRepositoryImpl(get()) }
}

val viewModelModule = module {
    viewModel { SummaryViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}
