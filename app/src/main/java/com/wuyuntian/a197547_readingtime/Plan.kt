package com.wuyuntian.a197547_readingtime

import android.database.Cursor
import java.time.Period

data class Plan(
    val book: Book,
    val reading_progress : Int = 0,
    val period: Int ,
    val current_day: Int=0,
)
