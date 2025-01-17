package com.wuyuntian.a197547_readingtime

import androidx.annotation.DrawableRes
import java.text.NumberFormat

data class Book(
    val title : String ,
    val author : List<String> ,
    val pages : Int,
    val year:Int? =null,
    var price : Double = 15.00 ,
    @DrawableRes val imageResourceID : Int
)

