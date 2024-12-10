package com.wuyuntian.a197547_readingtime.model


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wuyuntian.a197547_readingtime.R
import com.wuyuntian.a197547_readingtime.ppd
import com.wuyuntian.a197547_readingtime.ui.theme.ReadingTimeTheme
import kotlin.math.roundToInt

class ReadingPlan() {

}

@Composable
fun ReadingBookLayout(){
    //amount
    var pageInput by remember { mutableStateOf("") }
    val page = pageInput.toDoubleOrNull() ?: 0.0
    //tip rate
    var dayInput by remember { mutableStateOf("")}
    val day = dayInput.toDoubleOrNull() ?:1.0
    val  _dqwdwqd = CalculatePagesPerDay(page,day)
    //calculate ppd
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ){

        Text(
            text = "Make a Reading Plan",
            fontSize = 24.sp,
            modifier = Modifier
                .padding(bottom = 24.dp, top = 40.dp)
                .align(alignment = Alignment.Start),
        )
        Image(
            painter = painterResource(R.drawable.book),
            contentDescription = "",

            )
        //main
        EditNumberField(
            pageInput,
            { pageInput =it },
            dayInput,
            { dayInput = it },
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )
        Text(
            text = stringResource(R.string.read_plan,ppd),
            style = MaterialTheme.typography.displaySmall,
            fontSize = 32.sp,
            modifier = Modifier,
            textAlign = Center,
        )
    }
}

@Composable
fun EditNumberField(
    pageInput : String,
    onePagesChange : (String) -> Unit,
    dayInput : String,
    onDayChange: (String) -> Unit,
    modifier: Modifier = Modifier) {

    //amount input
    TextField(
        value = pageInput,
        onValueChange = onePagesChange,
        modifier = modifier,
        label = {Text(stringResource(R.string.book_pages))},
        //add label
        singleLine = true,
        //   leadingIcon = { Icon(painter = painterResource(id = R.drawable.ic_launcher_foreground), null) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next),

        )
    //tip rate
    TextField(
        value = dayInput,
        onValueChange = onDayChange,
        modifier = modifier,
        label = {Text(stringResource(R.string.period))},
        singleLine = true,
        //    leadingIcon = { Icon(painter = painterResource(id = R.drawable.ic_launcher_foreground), null) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done),

        )

}

@Composable
private fun CalculatePagesPerDay(page : Double ,day : Double) : Double{
    if ( day ==0.0){
        return page
    }
    val ppd = page / day
    return ppd.roundToInt().toDouble()
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ReadingTimeTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ){
            ReadingBookLayout()
        }
    }
}