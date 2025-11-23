package com.wuyuntian.a197547_readingtime.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg plan: Plan)

    @Update
    fun update(vararg plan: Plan)

    @Delete
    fun delete(vararg plan: Plan)

    @Query("select * from plans where id = :id")
    fun getPlanById(id: Int): Plan

    @Query("select * from plans")
    fun getAllPlan(): List<Plan>
}