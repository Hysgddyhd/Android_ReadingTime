package com.wuyuntian.a197547_readingtime.model

import com.wuyuntian.a197547_readingtime.Book
import com.wuyuntian.a197547_readingtime.R
import java.time.LocalDate
import java.time.LocalDateTime

data class PlanUiState(
    val book:Book = Book(
        title="",
        author=listOf(""),
        pageCount = 0,
        imageResourceID = R.drawable.no_photo
    ),
    val currentPage:Int=0,
    val period : Int = 30,
    val priority: Int = 1,
    val start_day: LocalDate = LocalDate.now(),
)
