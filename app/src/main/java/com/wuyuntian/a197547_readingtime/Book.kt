package com.wuyuntian.a197547_readingtime

import androidx.annotation.DrawableRes

data class Book(
    val title : String ,
    val author : List<String> ,
    val pages : Int,
    var price : Double = 15.00 ,
    @DrawableRes val imageResourceID : Int
)
