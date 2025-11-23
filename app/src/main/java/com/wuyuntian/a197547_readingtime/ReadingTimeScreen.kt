package com.wuyuntian.a197547_readingtime
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.navigation.NavController
import androidx.navigation.compose.composable
import com.wuyuntian.a197547_readingtime.dataSource.DataSource
import com.wuyuntian.a197547_readingtime.model.ReadingBookLayout
import com.wuyuntian.a197547_readingtime.model.SummaryScreen
import com.wuyuntian.a197547_readingtime.model.WelcomePage
import com.wuyuntian.a197547_readingtime.room.Plan
import com.wuyuntian.a197547_readingtime.ui.BooklistScreen
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date


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
    sharedPref: SharedPreferences,
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
//        bottomBar = {
//          BottomNavigation {
//            val navBackStackEntry by navController.currentBackStackEntryAsState()
//            val currentDestination = navBackStackEntry?.destination
//            topLevelRoutes.forEach { topLevelRoute ->
//              BottomNavigationItem(
//                icon = { Icon(topLevelRoute.icon, contentDescription = topLevelRoute.name) },
//                label = { Text(topLevelRoute.name) },
//                selected = currentDestination?.hierarchy?.any {
//                    it.hasRoute(
//                        topLevelRoute.route::class.toString(),
//                        arguments = null
//                    )
//                } == true,
//                onClick = {
//                    navController.navigate(topLevelRoute.route) {
//                    // Pop up to the start destination of the graph to
//                    // avoid building up a large stack of destinations
//                    // on the back stack as users select items
//                    popUpTo(navController.graph.findStartDestination().id) {
//                      saveState = true
//                    }
//                    // Avoid multiple copies of the same destination when
//                    // reselecting the same item
//                    launchSingleTop = true
//                    // Restore state when reselecting a previously selected item
//                    restoreState = true
//                  }
//                }
//              )
//            }
//          }
//        },
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
                    onContinueButtonClicked = {
                        viewModel.loadPlan(getPlanFromPref(sharedPref = sharedPref))
                        navController.navigate(
                            ReadingTimeScreen.Summary.name
                        )
                    },
                    onStartButtonClicked = {
                        navController.navigate(
                            ReadingTimeScreen.Booklist.name
                        )
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium)),
                    gotopPlanDB = { }
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
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.padding_medium))
                        .verticalScroll(rememberScrollState())

                )
            }
            composable(route=ReadingTimeScreen.Planning.name){
                    ReadingBookLayout(
                        uiState.book,
                        onCancelButtonClicked = {
                           cancelOrderAndNavigateToStart(viewModel,navController)
                        },
                        onNextButtonClicked = {
                            savePlan(sharedPref = sharedPref, newPlan = viewModel.toPlan())
                            navController.navigate(ReadingTimeScreen.Summary.name)
                        },
                        onDayChange = {
                            viewModel.updatePeriod(it)
                        },
                        onPrioChange = {
                            viewModel.updatePriority(it)
                        },
                        modifier = Modifier
                            .padding(dimensionResource(R.dimen.padding_medium))
                            .verticalScroll(rememberScrollState())
                            .fillMaxSize()
                    )
            }
            composable(route=ReadingTimeScreen.Summary.name){
                val context = LocalContext.current
                    SummaryScreen(
                        plan = Plan(
                            book = uiState.book,
                            priority = uiState.priority,
                            reading_progress = uiState.currentPage,
                            period = uiState.period,
                        ),
                        onClicked = {
                            navController.popBackStack(ReadingTimeScreen.Welcome.name, inclusive = false)

                                    },
                        onClickShare = {
                            sharePlan(
                                intentContext = context,
                                title = uiState.book.title,
                                period = uiState.period,
                                current_page = uiState.currentPage,
                                curretn_day = Date().day
                            )
                        },
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
                    )
            }
            composable(route=ReadingTimeScreen.Books.name){
                    BooklistScreen(
                        booklist = DataSource.BookList,
                        modifier = Modifier
                            .padding(dimensionResource(R.dimen.padding_medium))
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

private fun savePlan(sharedPref: SharedPreferences, newPlan:Plan){
    sharedPref.edit(){
        //plan id
        //putString("plan_id), uiState.)
        //plan priority
        putInt("plan_priority", newPlan.priority)
        //reading progress
        putInt("plan_reading_progress", newPlan.book.pageCount)
        //plan period
        putInt("plan_period", newPlan.period)
        //plan start day
        putString("plan_start_day", newPlan.start_day.toString())

        //book title
        putString("book_title", newPlan.book.title)
        //book author
        putString("book_author", newPlan.book.author.joinToString(","))
        //book pages count
        putInt("book_pages", newPlan.book.pageCount)
        //book publish year
        newPlan.book.year?.let { value -> putInt("book_publish_year", value) }
        //book price
        putFloat("book_price", newPlan.book.price)
        //book thumbnail
        putInt("book_thumbnail", newPlan.book.imageResourceID)
        apply()
    }
}

private fun getPlanFromPref(sharedPref: SharedPreferences): Plan{
    val book_title = sharedPref.getString("book_title", "")
    val book_author = sharedPref.getString("book_author", "")
    val book_publich_year=sharedPref.getInt("book_publish_year", 1)
    val book_pageCount = sharedPref.getInt("book_pageCount", 1)
    val book_price = sharedPref.getFloat("book_price", 1f)
    val book_thumbnail = sharedPref.getInt("book_thumbnail", R.drawable.no_photo)
    val plan_period= sharedPref.getInt("plan_period", 1)
    val plan_priority= sharedPref.getInt("plan_priority", 1)
    val plan_reading_progress= sharedPref.getInt("plan_reading_progress", 1)
    val plan_start_day= sharedPref.getString("plan_start_day", null)
    return Plan(
         book = Book(
             title = book_title.toString(),
             author = book_author?.split(",") ?: listOf("Richard Blum"),
             pageCount = book_pageCount,
             year = book_publich_year,
             price = book_price,
             imageResourceID = book_thumbnail
         ),
         priority = plan_priority,
         reading_progress = plan_reading_progress,
         period = plan_period,
         start_day = LocalDate.parse(plan_start_day)
     )

}

//@Preview
//@Composable
//fun ReadingPlan(){
//    ReadingTimeTheme {
//        ReadingTimeApp()
//    }
//
//}

data class TopLevelRoute<T : Any>(
  val name: String,
  val route: T,
  val icon: ImageVector
)
