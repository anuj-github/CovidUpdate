package com.graduateguy.myapplication.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.graduateguy.myapplication.room.dao.CountryDao
import com.graduateguy.myapplication.room.dao.GlobalSummaryDao
import com.graduateguy.myapplication.room.entity.CountryInfo
import com.graduateguy.myapplication.room.entity.GlobalSummary

@Database(
    version = 1,
    entities = [
        GlobalSummary::class,
        CountryInfo::class
    ]
)
abstract class CovidDatabase : RoomDatabase() {
    abstract val globalSummaryDao: GlobalSummaryDao
    abstract val countrydao: CountryDao

    companion object{

        private var covidDatabse:CovidDatabase?=null
        private val DB_NAME = "coviddb"
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