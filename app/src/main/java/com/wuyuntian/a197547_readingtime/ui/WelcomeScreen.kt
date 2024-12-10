package com.wuyuntian.a197547_readingtime.model

import androidx.annotation.DimenRes
import androidx.annotation.Dimension
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wuyuntian.a197547_readingtime.ui.theme.ReadingTimeTheme


@Composable
fun WelcomePage(
    onStartButtonClicked : () -> Unit,

){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ){
        Text(
            text = "Reading Time",
            color = Color.Blue,
            fontSize = 42.sp

        )
        Spacer(modifier = Modifier.height(150.dp))
        Button(
            onClick = onStartButtonClicked,
            Modifier.widthIn(min = 200.dp)
        ){
            Text("Start", fontSize = 24.sp)
        }
    }
}


@Preview
@Composable
fun Welcome(){
    ReadingTimeTheme{
        WelcomePage(onStartButtonClicked = {})
    }
}