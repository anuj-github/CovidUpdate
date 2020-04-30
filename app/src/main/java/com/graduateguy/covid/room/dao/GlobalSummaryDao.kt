package com.graduateguy.covid.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.graduateguy.covid.room.entity.GlobalSummary

@Dao
interface GlobalSummaryDao {

    @Query("SELECT * from GlobalSummary")
    fun getSummary(): LiveData<GlobalSummary>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(summary: GlobalSummary)

    @Query("DELETE from GlobalSummary")
    fun delete()
}