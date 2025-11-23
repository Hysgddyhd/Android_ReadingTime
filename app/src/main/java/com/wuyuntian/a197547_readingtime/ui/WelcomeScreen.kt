package com.wuyuntian.a197547_readingtime.model

import android.util.DisplayMetrics
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.window.layout.WindowMetricsCalculator
import com.wuyuntian.a197547_readingtime.ui.theme.ReadingTimeTheme


@Composable
fun WelcomePage(
    onContinueButtonClicked : () -> Unit,
    onStartButtonClicked : () -> Unit,
    gotopPlanDB: () -> Unit,
    modifier: Modifier,
    ){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ){
//        Text(
//            text = "Reading Time",
//            color = Color.Blue,
//            fontSize = 42.sp
//
//        )
        val windowRect = LocalContext.current.resources.displayMetrics
//        Button(
//            onClick = gotopPlanDB,
//            Modifier.height(height = 150.dp)
//                .width(width = 400.dp)
//        ){
//            Text("View Plan DB", fontSize = 24.sp)
//        }
//        Spacer(modifier = Modifier.height(height = 100.dp))
        Button(
            onClick = onContinueButtonClicked,
            Modifier.height(height = 150.dp)
                .width(width = 400.dp)
        ){
            Text("Continue", fontSize = 24.sp)
        }
        Spacer(modifier = Modifier.height(height = 100.dp))

        Button(
            onClick = onStartButtonClicked,
            Modifier.height(height = 150.dp)
                .width(width = 400.dp)
        ){
            Text("Create new plan", fontSize = 24.sp)
        }
    }
}


@Preview
@Composable
fun Welcome(){
    ReadingTimeTheme{
        WelcomePage(
            gotopPlanDB = {},
            onContinueButtonClicked ={},
            onStartButtonClicked = {},
            modifier = Modifier
        )

    }
}