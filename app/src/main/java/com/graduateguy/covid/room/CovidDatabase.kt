package com.graduateguy.covid.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.graduateguy.covid.room.dao.CountryDao
import com.graduateguy.covid.room.dao.GlobalSummaryDao
import com.graduateguy.covid.room.entity.CountryInfo
import com.graduateguy.covid.room.entity.GlobalSummary

@Database(
    version = 1, exportSchema = false,
    entities = [
        GlobalSummary::class,
        CountryInfo::class
    ]
)
abstract class CovidDatabase : RoomDatabase() {
    abstract val globalSummaryDao: GlobalSummaryDao
    abstract val countrydao: CountryDao

    companion object {

        private var covidDatabse: CovidDatabase? = null
        val DB_NAME = "coviddb"
        fun getCovidDatabase(context: Context): CovidDatabase {
            if (covidDatabse == null) {
                covidDatabse = Room.databaseBuilder(
                    context,
                    CovidDatabase::class.java,
                    DB_NAME
                )
                    .build()
            }
            return covidDatabse!!
        }
    }
}
