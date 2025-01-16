package com.wuyuntian.a197547_readingtime.model

import com.wuyuntian.a197547_readingtime.Book
import com.wuyuntian.a197547_readingtime.room.Plan
import com.wuyuntian.a197547_readingtime.R
import java.util.Date

data class PlanUiState(
    val booklist :  List<Book> = listOf(),
    val book:Book = Book("", listOf(""), pages = 0, imageResourceID = R.drawable.no_photo),
    val plan : Plan = Plan(book = book, period = 10, current_day = Date()),
    val bookCount:Int = 0,
    val pages:Int=0,
    val pageCount:Int=0,
    val pagePerDay: Double=0.0,

    val period : Int? = null,
    val current_day: Int =0,
)
