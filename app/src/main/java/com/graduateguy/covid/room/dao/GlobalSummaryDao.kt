package com.graduateguy.covid.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.graduateguy.covid.room.entity.GlobalSummary
import kotlinx.coroutines.flow.Flow

@Dao
interface GlobalSummaryDao {

    @Query("SELECT * from GlobalSummary")
    fun getSummary(): Flow<GlobalSummary>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(summary: GlobalSummary)

    @Query("DELETE from GlobalSummary")
    fun delete()
}