package com.graduateguy.covid.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.graduateguy.covid.room.entity.CountryInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {

    @Query("SELECT * from CountryInfo where country=:country")
    fun getSummaryForCountry(country: String): Flow<CountryInfo>

    @Query("SELECT * from CountryInfo ORDER BY totalConfirmed desc")
    fun getAllCountry(): Flow<List<CountryInfo>>

    @Query("SELECT * from CountryInfo where country LIKE :searchKey")
    fun getCountry(searchKey: String): Flow<List<CountryInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(country: CountryInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(countryList: List<CountryInfo>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(country: CountryInfo)

    @Delete
    fun delete(country: CountryInfo)
}
