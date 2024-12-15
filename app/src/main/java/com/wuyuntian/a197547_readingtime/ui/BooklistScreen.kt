package com.wuyuntian.a197547_readingtime.ui



import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.wuyuntian.a197547_readingtime.Book
import com.wuyuntian.a197547_readingtime.R
import com.wuyuntian.a197547_readingtime.dataSource.DataSource

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