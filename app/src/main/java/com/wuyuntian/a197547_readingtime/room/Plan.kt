package com.wuyuntian.a197547_readingtime.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wuyuntian.a197547_readingtime.Book
import java.util.Date

@Entity(tableName = "plans")
data class Plan(
    @PrimaryKey(autoGenerate = true)
    var id: Int =0,
    val book: Book,
    val priority:Int=0,
    val reading_progress : Int = 0,
    val period: Int,
    val current_day: Date=Date(),

    )
