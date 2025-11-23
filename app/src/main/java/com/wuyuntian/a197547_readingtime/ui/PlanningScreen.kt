package com.wuyuntian.a197547_readingtime.model


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wuyuntian.a197547_readingtime.Book
import com.wuyuntian.a197547_readingtime.R
import com.wuyuntian.a197547_readingtime.ui.theme.Shapes


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ReadingBookLayout(
    selected_book: Book,
    onCancelButtonClicked: () -> Unit = {},
    onNextButtonClicked: () -> Unit = {},
    onDayChange: (String) -> Unit,
    onPrioChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    //tip rate
    var readDay by remember { mutableStateOf(0) }
    var priority by remember { mutableStateOf(0) }
    //calculate ppd
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(vertical = 15.dp, horizontal = 10.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = stringResource(R.string.make_a_plan),
            fontSize = 28.sp,
            textAlign = Center,
            modifier = Modifier
                .padding(bottom = 24.dp, top = 40.dp),
        )
        Text(
            text = selected_book.title,
            fontSize = 24.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .align(Alignment.Start)
        )
        Image(
            painter = painterResource(selected_book.imageResourceID),
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .width(200.dp)
        )
        //amount input
        Text(
            text = "Pages: " + selected_book.pageCount.toString(),
            fontSize = 28.sp,
            modifier = Modifier
                .padding(19.dp)
        )
        Text(
            text = "I'll read: " + getReadingQuota(selected_book, readDay) + " pages per day.",
            fontSize = 24.sp,
            modifier = Modifier
                .padding(19.dp)
        )
        //main
        EditNumberField(
            dayInput = if (readDay!=0) readDay.toString() else "",
            onDayChange = {
                if (it == "") {
                    readDay = 0
                } else {
                    readDay = it.toInt()
                    onDayChange(it)
                }
            },
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )
        //priority add minus field
        Button(
            onClick = {
                priority = if (priority > 0) priority - 1 else 0
                onPrioChange(priority.toString())
            },
            enabled = true,
            shape = Shapes.medium,
            modifier = Modifier.height(48.dp)
            // border = BorderStroke(3.dp, Color.Black),
        ) {
            Text(
                text = "-",
                color = Color.Black,
                fontSize = 20.sp,
            )
        }
        EditPriorityField(
            priInput = priority.toString(),
            onInputChange = { },
            isEnable = false,
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )
        Button(
            onClick = {
                priority = if (priority < 10) priority + 1 else 10
                onPrioChange(priority.toString())
            },
            enabled = true,
            shape = Shapes.medium,
            modifier = Modifier.height(48.dp)
            //border = BorderStroke(3.dp, Color.Black),
        ) {
            Text(
                text = "+",
                color = Color.Black,
                fontSize = 32.sp,
            )
        }
        Spacer(
            Modifier.height(20.dp)
        )

        MenuScreenButtonGroup(
            selectedItemName = selected_book.title,
            onCancelButtonClicked = onCancelButtonClicked,
            onNextButtonClicked =
                // Assert not null bc next button is not enabled unless selectedItem is not null.
                onNextButtonClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium))
                .align(alignment = Alignment.End)
        )
    }
}

@Composable
fun EditNumberField(
    dayInput: String,
    onDayChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    //tip rate
    TextField(
        value = dayInput,
        onValueChange = onDayChange,
        modifier = modifier,
        label = { Text(stringResource(R.string.period)) },
        singleLine = true,
        //    leadingIcon = { Icon(painter = painterResource(id = R.drawable.ic_launcher_foreground), null) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
    )

}

@Composable
fun EditPriorityField(
    priInput: String,
    onInputChange: (String) -> Unit,
    isEnable: Boolean,
    modifier: Modifier = Modifier
) {
    //amount input

    //tip rate
    TextField(
        value = priInput,
        onValueChange = onInputChange,
        label = {
            Text("Priority", color = Color.Black)
        },
        enabled = isEnable,
        modifier = modifier,
        singleLine = true,
        //    leadingIcon = { Icon(painter = painterResource(id = R.drawable.ic_launcher_foreground), null) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = ImeAction.Done
        ),
    )

}

@Composable
fun getReadingQuota(selected_book: Book, readDay: Int): String {
    if (readDay == 0)
        return ""
    val quota = selected_book.pageCount.div(readDay.toDouble())
    return String.format("%.2f", quota)
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
    ) {
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


