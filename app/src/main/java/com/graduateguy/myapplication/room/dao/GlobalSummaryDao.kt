package com.graduateguy.myapplication.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.graduateguy.myapplication.room.entity.GlobalSummary

@Dao
interface GlobalSummaryDao {

    @Query("SELECT * from GlobalSummary")
    fun getSummary(): LiveData<GlobalSummary>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(summary: GlobalSummary)

    @Query("DELETE from GlobalSummary")
    fun delete()
}