package com.wuyuntian.a197547_readingtime.ui

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wuyuntian.a197547_readingtime.Book
import com.wuyuntian.a197547_readingtime.R
import com.wuyuntian.a197547_readingtime.dataSource.DataSource.BookList

@Composable
fun BookListScreen(){
       Scaffold { it ->
            LazyColumn ( contentPadding = it ){
                items(BookList){
                        BookCard(
                            book = it,
                            modifier = Modifier
                                .padding(8.dp)
                        )
                }
            }

       }
}

@Composable
fun BookCard(book : Book,modifier:Modifier = Modifier){
    Card(
        modifier=modifier
        .clip(MaterialTheme.shapes.small)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            BookIcon(book.imageResourceID)
            Column (
                modifier = Modifier
                    .fillMaxWidth()

            ){
                Text(
                    text = book.title
                )
                Text(
                    text = book.author.toString()
                )
            }
        }
    }
}

@Composable
fun BookIcon(@DrawableRes id : Int ){
    Image(
        modifier = Modifier
            .size(100.dp)
            .padding(8.dp)
            .clip(MaterialTheme.shapes.medium),
        painter = painterResource(id),
        contentDescription = null,

    )
}

@Preview
@Composable
fun showMeBook(){
    val book1 : Book =  Book(
            title = "Linux All-in-One for Dummies (7TH)",
            author = listOf("Richard Blum"),
            pages = 576,
            price = 157.83 ,
            imageResourceID = R.drawable.linux_dummy
            //reference : https://malaysia.kinokuniya.com/Linux_All-in-One_for_Dummies_(7TH)/bw/9781119901921
        )
    BookListScreen()
}