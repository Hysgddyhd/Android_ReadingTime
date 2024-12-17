package com.wuyuntian.a197547_readingtime.model

import android.graphics.Paint.Align
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wuyuntian.a197547_readingtime.Book
import com.wuyuntian.a197547_readingtime.Plan
import com.wuyuntian.a197547_readingtime.R
import com.wuyuntian.a197547_readingtime.dataSource.DataSource.BookList
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.text.style.TextAlign.Companion.End


@Composable
fun SummaryScreen(
    plan:Plan,
    modifier: Modifier,
    onClicked: () -> Unit,
    onClickShare: () -> Unit

){
     var selectedBooks by rememberSaveable { mutableStateOf("") }

       Column (
           modifier = Modifier
               .fillMaxSize()
               .padding(4.dp)
       ) {

           PlanCard(
               book = plan.book,
               plan ,
               modifier = modifier,
               onClickShare = onClickShare
           )


       }
           bottomButton(modifier=modifier,onClicked=onClicked)
}





@Composable
fun bottomButton(
    modifier: Modifier,
    onClicked: () -> Unit
){
    Column (
        modifier = modifier,
         horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom,

    ) {
        Button(
            onClick = onClicked,
            content = {
                Text(
                    text="Home",
                    fontSize = 18.sp
                )
            },
            modifier = Modifier
        )
    }
}
@Composable
fun PlanCard(book : Book,
             plan : Plan,
             modifier:Modifier = Modifier,
             onClickShare : () -> Unit
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){

        Card(
            modifier = modifier
                .clip(MaterialTheme.shapes.small)
                .fillMaxWidth()
                .height(350.dp),

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                BookIcon(book.imageResourceID)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    Text(
                        text = book.title,
                        fontSize = 20.sp,

                    )
                    Text(
                        text = book.author.toString(),
                        fontSize = 16.sp,
                        textAlign = Center
                    )
                    Text(
                        text = stringResource(R.string.page_progress,plan.reading_progress,book.pages),
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text ="Day: "+ plan.current_day.toString()+"/"+plan.period.toString(),
                        fontSize = 18.sp,
                        textAlign = End
                    )
                    IconButton(

                        content =  {
                            Text(
                                "Share",
                                color = Color.Blue,
                                fontSize = 14.sp,
                                textAlign = End ,
                                fontWeight = FontWeight.Bold
                            )


                        },
                        onClick = onClickShare
                )
                    /*Icon(
                                painter = painterResource(R.drawable.share),
                                "",
                                modifier = Modifier
                                .width(45.dp)
                                .height(45.dp),

                            )*/
                }
            }
        }
    }
}

@Composable
fun BookIcon(@DrawableRes id : Int ){
    Image(
        modifier = Modifier
            .width(120.dp)
            .height(210.dp)
            .padding(8.dp)
            .clip(MaterialTheme.shapes.medium),

        painter = painterResource(id),
        contentDescription = null,
        contentScale = ContentScale.FillWidth

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
  SummaryScreen(
      plan = Plan(book1,0,10),
      modifier = Modifier
        .padding(dimensionResource(R.dimen.padding_small))
        .fillMaxWidth(),

      onClicked = {},
      onClickShare = {}
      )
}