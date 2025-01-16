package com.wuyuntian.a197547_readingtime.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import kotlinx.coroutines.flow.Flow

@Database(entities = [Plan::class],version=1)
abstract class PlanDatabase:RoomDatabase() {
    abstract fun planDao():PlanDao

    companion object{
        @Volatile
        private var Instance: PlanDatabase? = null

        fun getDatabase(context: Context): PlanDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    PlanDatabase::class.java,
                    "item_database")
                    //.createFromAsset("database/goods_start_example.db")
                    .build()
                    .also {
                        Instance = it
                    }
            }
        }
    }
}