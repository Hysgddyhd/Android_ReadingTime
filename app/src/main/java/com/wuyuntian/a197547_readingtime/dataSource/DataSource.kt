package com.wuyuntian.a197547_readingtime.dataSource

import com.wuyuntian.a197547_readingtime.Book
import com.wuyuntian.a197547_readingtime.R

object DataSource {
    val BookList = listOf(
        Book(
            title = "Linux All-in-One for Dummies (7TH)",
            author = listOf("Richard Blum"),
            pages = 576,
            price = 157.83 ,
            imageResourceID = R.drawable.linux_dummy
            //reference : https://malaysia.kinokuniya.com/Linux_All-in-One_for_Dummies_(7TH)/bw/9781119901921
        ),
        Book(
            title = "internet_w3_how_to_program_5ed",
            author = listOf("Paul Deitel","Harvey Deitel","Abbey Deitel"),
            pages = 1202,
            imageResourceID = R.drawable.internet_program,
        ),
        Book(
            title = "beginners_guide_data_science_sample",
            author = listOf("Vinod Chugani"),
            pages = 35,
            imageResourceID = R.drawable.beginners_guide_data_science_sample,
            price = 0.0
        ),
        Book(
            title = "Compilers Second Edition Principles, Techniques, & Tools",
            author = listOf("Alfred V. Aho","Monica S. Lam","Ravi Sethi"),
            pages = 1035,
            imageResourceID = R.drawable.compilers,

        ),
        Book(
            title = "Real-World Android by Tutorials",
            author = listOf("Ricardo Costeira","Subhrajyoti Sen","Antonio Roa-Valverde"),
            pages = 519,
            imageResourceID = R.drawable.android_tutorial
        ),
        Book(
            title = "Linux robotics : programming smarter robots",
            author= listOf("D. Jay Newman"),
            pages = 287,
            year=2006,
            imageResourceID =R.drawable.linux_rotobics ,
        ),
        Book(
            title = "Linux bible",
            author= listOf("Christopher Negus"),
            pages=914,
            year = 2015,
            imageResourceID = R.drawable.linux_bible,
        ),
        Book(
            title = "Introduction to Python programming",
            author = listOf("Gowrishankar S, Veena A."),
            year=2018,
            pages = 444,
            imageResourceID = R.drawable.intro_python,
        ),
        Book(
            title = "Java : an introduction to problem solving & programming",
            author = listOf("Walter Savitch","Kenrick Mock"),
            year=2019,
            pages = 1016,
            imageResourceID = R.drawable.no_photo,
        )
    )

}