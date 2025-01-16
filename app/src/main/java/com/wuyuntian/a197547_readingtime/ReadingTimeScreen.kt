package com.wuyuntian.a197547_readingtime
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.provider.ContactsContract.Data
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
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
    Books,
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
        title = { Text(currentScreen.name.toString()) },
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



@SuppressLint("RestrictedApi")
@Composable
fun ReadingTimeApp(
    navController : NavHostController = rememberNavController()

){
    val viewModel: PlanViewModel = viewModel()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = ReadingTimeScreen.valueOf(
            backStackEntry?.destination?.route ?: ReadingTimeScreen.Welcome.name
    )
    val topLevelRoutes = listOf(
            TopLevelRoute("Plan", ReadingTimeScreen.Summary.name, Icons.Filled.Home),
            TopLevelRoute("Add",ReadingTimeScreen.Welcome.name, Icons.Filled.Add),
            TopLevelRoute("Book",ReadingTimeScreen.Books.name, Icons.Default.Info)
    )
    val canNotNaviBack=navController.currentDestination?.route==ReadingTimeScreen.Welcome.name ||
            navController.currentDestination?.route==ReadingTimeScreen.Books.name ||
            navController.currentDestination?.route==ReadingTimeScreen.Summary.name
    Scaffold(
        topBar = {
            OrderAppBar(
                navigateUp = {
                    navController.navigateUp()
                },
                canNavigateBack = !canNotNaviBack,
                currentScreen = currentScreen
            )
        },
        bottomBar = {
          BottomNavigation {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            topLevelRoutes.forEach { topLevelRoute ->
              BottomNavigationItem(
                icon = { Icon(topLevelRoute.icon, contentDescription = topLevelRoute.name) },
                label = { Text(topLevelRoute.name) },
                selected = currentDestination?.hierarchy?.any {
                    it.hasRoute(
                        topLevelRoute.route::class.toString(),
                        arguments = null
                    )
                } == true,
                onClick = {
                    navController.navigate(topLevelRoute.route) {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    popUpTo(navController.graph.findStartDestination().id) {
                      saveState = true
                    }
                    // Avoid multiple copies of the same destination when
                    // reselecting the same item
                    launchSingleTop = true
                    // Restore state when reselecting a previously selected item
                    restoreState = true
                  }
                }
              )
            }
          }
        },
        modifier = Modifier.heightIn(max=875.dp)

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
                           cancelOrderAndNavigateToStart(viewModel,navController)

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
                        uiState.plan,
                        day_input = viewModel.day_input,
                        priority_input = viewModel.priority_input,
                        onCancelButtonClicked = {
                           cancelOrderAndNavigateToStart(viewModel,navController)
                        },
                        onNextButtonClicked = {
                            navController.navigate(ReadingTimeScreen.Summary.name)

                        },
                        onInputChange = {
                            viewModel.updateDayInput(it)
                        },
                        onPrioChange = {
                            viewModel.updatePriority(it)
                        },
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
                            .verticalScroll(rememberScrollState())
                            .fillMaxSize()
                    )
            }
            composable(route=ReadingTimeScreen.Summary.name){
                val context = LocalContext.current
                    SummaryScreen(
                        plan = uiState.plan,
                        onClicked = {
                            navController.popBackStack(ReadingTimeScreen.Welcome.name, inclusive = false)

                                    },
                        onClickShare = {
                            sharePlan(
                                intentContext = context,
                                title = viewModel.select_book.title,
                                period = uiState.plan.period,
                                current_page = uiState.plan.reading_progress,
                                curretn_day = uiState.plan.current_day.day
                            )
                        },
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
                    )
            }
            composable(route=ReadingTimeScreen.Books.name){
                    BooklistScreen(
                        booklist = DataSource.BookList,
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
                            .verticalScroll(rememberScrollState())
                            .fillMaxSize()
                    )
            }
        }
    }
}



private fun cancelOrderAndNavigateToStart(
    viewModel : PlanViewModel,
    navController: NavController
) {
    viewModel.resetPlan()
    navController.popBackStack(ReadingTimeScreen.Welcome.name, inclusive = false)
}

private fun  sharePlan(
    intentContext : Context,
    title : String,
    period: Int,
    current_page : Int,
    curretn_day:Int
){
    val intent= Intent(Intent.ACTION_SEND).apply{
        type = "text/plain"
        putExtra(
            Intent.EXTRA_TEXT,
            intentContext.getString(R.string.share_plan,title,curretn_day,period,current_page)
        )
    }
    intentContext.startActivity(
        Intent.createChooser(
            intent,
            intentContext.getString(R.string.new_plan)
        )
    )

}

@Preview
@Composable
fun ReadingPlan(){
    ReadingTimeTheme {
        ReadingTimeApp()
    }

}

data class TopLevelRoute<T : Any>(
  val name: String,
  val route: T,
  val icon: ImageVector
)
