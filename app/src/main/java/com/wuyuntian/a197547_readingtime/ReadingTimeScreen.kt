package com.wuyuntian.a197547_readingtime
import android.provider.ContactsContract.Data
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wuyuntian.a197547_readingtime.ui.PlanViewModel
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.composable
import com.wuyuntian.a197547_readingtime.dataSource.DataSource
import com.wuyuntian.a197547_readingtime.model.ReadingBookLayout
import com.wuyuntian.a197547_readingtime.model.SummaryScreen
import com.wuyuntian.a197547_readingtime.model.WelcomePage
import com.wuyuntian.a197547_readingtime.ui.BooklistScreen
import com.wuyuntian.a197547_readingtime.ui.theme.ReadingTimeTheme


enum class ReadingTimeScreen() {
    Welcome,
    Booklist,
    Planning,
    Summary,
}
// TODO: AppBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OrderAppBar(
    navigateUp : ()-> Unit,
    canNavigateBack : Boolean,
    currentScreen : ReadingTimeScreen,
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Gray
        ),
        modifier = modifier,
        navigationIcon = {
            if(canNavigateBack){
                IconButton(onClick = navigateUp){
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}



@Composable
fun ReadingTimeApp(
    navController : NavHostController = rememberNavController()

){
    val viewModel: PlanViewModel = viewModel()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = ReadingTimeScreen.valueOf(
            backStackEntry?.destination?.route ?: ReadingTimeScreen.Welcome.name
    )
    Scaffold(
        topBar = {
            // TODO: AppBar
            OrderAppBar(
                navigateUp = {},
                canNavigateBack = navController.previousBackStackEntry != null,
                currentScreen = currentScreen
            )
        }
    ){innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController=navController,
            startDestination = ReadingTimeScreen.Welcome.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route=ReadingTimeScreen.Welcome.name){
                WelcomePage(
                    onStartButtonClicked = {
                        navController.navigate(ReadingTimeScreen.Booklist.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }
            composable(route=ReadingTimeScreen.Booklist.name){
                BooklistScreen(
                    options = DataSource.BookList,
                    onCancelButtonClicked = {
                        navController.navigate(ReadingTimeScreen.Welcome.name)

                    },
                    onNextButtonClicked = {
                        navController.navigate(ReadingTimeScreen.Planning.name)
                    },
                    onSelectionChanged = {
                        viewModel.updateSelectedBook(it)

                    },
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
                    .verticalScroll(rememberScrollState())

                )
            }
            composable(route=ReadingTimeScreen.Planning.name){
                    ReadingBookLayout(
                        uiState.book,
                        days = uiState.plan.period,
                        onCancelButtonClicked = {
                            navController.navigate(ReadingTimeScreen.Welcome.name)
                        },
                        onNextButtonClicked = {
                            navController.navigate(ReadingTimeScreen.Summary.name)

                        },
                        onInputChange = {
                            viewModel.updatePlan(it)
                        }
                    )
            }
            composable(route=ReadingTimeScreen.Summary.name){
                    SummaryScreen(
                        plan = uiState.plan,
                        onClicked = {
                            navController.navigate(ReadingTimeScreen.Welcome.name)
                        },
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
                    )
            }
        }
    }
}

@Preview
@Composable
fun ReadingPlan(){
    ReadingTimeTheme {
        ReadingTimeApp()
    }

}