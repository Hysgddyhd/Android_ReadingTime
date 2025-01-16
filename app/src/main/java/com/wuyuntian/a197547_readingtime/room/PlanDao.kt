package com.wuyuntian.a197547_readingtime.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanDao {
    @Insert
    fun insert(plan: Plan): Long

    @Update
    fun update(plan: Plan)

    @Delete
    fun delete(plan: Plan)

    @Query("select * from plans")
    fun getPlan(): Flow<List<Plan>>
}