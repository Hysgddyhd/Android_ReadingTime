package com.wuyuntian.a197547_readingtime.model


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wuyuntian.a197547_readingtime.Book
import com.wuyuntian.a197547_readingtime.R
import com.wuyuntian.a197547_readingtime.ui.theme.ReadingTimeTheme
import kotlin.math.roundToInt

class ReadingPlan() {

}

@Composable
fun ReadingBookLayout(
    book1: Book,
    day_input : String,
     onCancelButtonClicked: () -> Unit = {},
    onNextButtonClicked: () -> Unit = {},
    onInputChange: (String) -> Unit
){

    //tip rate

    val  ppd = CalculatePagesPerDay(book1.pages.toDouble(),day_input)
    //calculate ppd
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .padding(vertical = 15.dp, horizontal = 10.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,


        ){

        Text(
            text = stringResource(R.string.make_a_plan),
            fontSize = 24.sp,
            modifier = Modifier
                .padding(bottom = 24.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
                ,
        )
        Text(
            text = book1.title,
            fontSize = 24.sp,
            textAlign = Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .align(Alignment.Start)
        )
        Image(
            painter = painterResource(book1.imageResourceID),
            contentDescription = "",
            contentScale = ContentScale.FillWidth

            )
        //main
        EditNumberField(
            book1.pages,
            day_input,
            onInputChange,
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
        Spacer(
            Modifier.height(120.dp)
        )
        MenuScreenButtonGroup(
            selectedItemName = book1.title,
            onCancelButtonClicked = onCancelButtonClicked,
            onNextButtonClicked =
                // Assert not null bc next button is not enabled unless selectedItem is not null.
                onNextButtonClicked
            ,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium))
                .align(alignment = Alignment.End)
        )
    }
}

@Composable
fun EditNumberField(
    page : Int,
    dayInput : String,
    onDayChange: (String) -> Unit,
    modifier: Modifier = Modifier) {

    //amount input
    Text(
        text = "Pages: "+page.toString(),
        fontSize = 28.sp

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
private fun CalculatePagesPerDay(page : Double ,day_input : String) : Double{
    if ( day_input.equals("")){
        return page
    }
    val period = day_input.toDouble()
    if ( period ==0.0){
        return page
    }
    val ppd = page / period
    return ppd.roundToInt().toDouble()
}

@Composable
fun MenuScreenButtonGroup(
    selectedItemName: String,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
    ){
        OutlinedButton(modifier = Modifier.weight(1f), onClick = onCancelButtonClicked) {
            Text(stringResource(R.string.cancel).uppercase())
        }
        Button(
            modifier = Modifier.weight(1f),
            // the button is enabled when the user makes a selection
            enabled = selectedItemName.isNotEmpty(),
            onClick = onNextButtonClicked
        ) {
            Text(stringResource(R.string.next).uppercase())
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val book1 : Book =  Book(
            title = "Linux All-in-One for Dummies (7TH)",
            author = listOf("Richard Blum"),
            pages = 576,
            price = 157.83 ,
            imageResourceID = R.drawable.linux_dummy
            //reference : https://malaysia.kinokuniya.com/Linux_All-in-One_for_Dummies_(7TH)/bw/9781119901921
        )
    ReadingTimeTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ){
            ReadingBookLayout(book1, onInputChange = {}, day_input = "")
        }
    }
}