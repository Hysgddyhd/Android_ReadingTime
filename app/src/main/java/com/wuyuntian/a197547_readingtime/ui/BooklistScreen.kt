package com.wuyuntian.a197547_readingtime.ui



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wuyuntian.a197547_readingtime.Book
import com.wuyuntian.a197547_readingtime.R
import com.wuyuntian.a197547_readingtime.dataSource.DataSource
import com.wuyuntian.a197547_readingtime.dataSource.DataSource.BookList
import com.wuyuntian.a197547_readingtime.dataSource.DataSource.book_sample
import com.wuyuntian.a197547_readingtime.ui.theme.Shapes

@Composable
fun BooklistScreen(
    options: List<Book>,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onSelectionChanged: (Book) -> Unit,
    modifier: Modifier = Modifier
){
    BaseMenuScreen(
        options = options,
        onCancelButtonClicked = onCancelButtonClicked,
        onNextButtonClicked = onNextButtonClicked,
        onSelectionChanged = onSelectionChanged ,
        modifier = modifier
    )
}

@Composable
fun BooklistScreen(
    booklist: List<Book>,
    modifier: Modifier = Modifier
){
    Column (
        modifier=modifier
            .padding(6.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),

    ){
        booklist.forEach { item ->
            BookCard(item)
            }
    }
}

@Composable
fun BookCard(
    book:Book,
    modifier: Modifier=Modifier
){
    Card (
        modifier= modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(180.dp),
        shape = Shapes.large

    ){
        Row(modifier=modifier.padding(4.dp),
            horizontalArrangement = Arrangement.Center) {
            Image(painter = painterResource(book.imageResourceID), contentScale = ContentScale.FillHeight, contentDescription = null)
            Column(
                modifier=modifier.fillMaxWidth().padding(6.dp),
                verticalArrangement = Arrangement.Center
            ){
                Text(text = book.title, fontSize = 18.sp)
                Text(text = book.author.toString(),fontSize = 14.sp)
            }
        }

    }
}


@Preview
@Composable
fun BooklistScreenPreview(){
    BooklistScreen(
        booklist = BookList, modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
    )
}
@Preview
@Composable
fun BookCardPreview(){
    BookCard(book = book_sample)
}
@Preview
@Composable
fun showMeBook(){

    BooklistScreen(
        options = DataSource.BookList,
        onCancelButtonClicked = {},
        onNextButtonClicked = {},
        onSelectionChanged = {},
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_medium))
            .verticalScroll(rememberScrollState())
    )
}