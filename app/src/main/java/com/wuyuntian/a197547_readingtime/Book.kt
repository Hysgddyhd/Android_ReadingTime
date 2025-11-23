package com.wuyuntian.a197547_readingtime

import androidx.annotation.DrawableRes

data class Book(
    val title : String,
    val author : List<String>,
    val pageCount : Int,
    val year:Int? =null,
    var price : Float = 15.00f,
    @DrawableRes val imageResourceID : Int
)

