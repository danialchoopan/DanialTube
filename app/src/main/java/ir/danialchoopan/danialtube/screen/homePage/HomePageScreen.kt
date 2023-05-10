package ir.danialchoopan.danialtube.screen

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Icon
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ir.danialchoopan.danialtube.screen.search.searchCourseScreen
import ir.danialchoopan.danialtube.viewmodels.HomeScreenViewModel
import kotlinx.coroutines.launch


@Composable
fun HomePageScreen(navController: NavController) {
    val homeScreenViewModel: HomeScreenViewModel = viewModel()
    val m_context = LocalContext.current
    val scaffoldState =
        rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
    val coroutin1 = rememberCoroutineScope()
    var bottom_navigation_switch_number by remember {
        mutableStateOf("home")
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        coroutin1.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "")
                    }
                },
                title = { Text("DanialTube") })
        },
        drawerContent = { drawerContentScaffold(m_context,navController,homeScreenViewModel) },
        content = {
            when (bottom_navigation_switch_number) {
                "home" -> {
                    HomePageScreenScaffoldContent(m_context,navController, homeScreenViewModel)
                }//profile
                "search" -> {
                    searchCourseScreen(navController = navController)
                }//search
                "profile" -> {

                    //user auth
                    UserAuthScreenLoginRegisterProfile(m_context, homeScreenViewModel,navController){
                        bottom_navigation_switch_number=it
                    }
                    //user auth

                }//user profile bottom view

            }
        },
        bottomBar = {
            BottomNavigation() {
                BottomNavigationItem(
                    selected = bottom_navigation_switch_number == "home",
                    onClick = {
                        bottom_navigation_switch_number = "home"
                    },
                    icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "") },
                    label = { Text(text = "صفحه اصلی") },
                )

                BottomNavigationItem(
                    selected = bottom_navigation_switch_number == "search",
                    onClick = {
                        bottom_navigation_switch_number = "search"
                    },
                    icon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
                    label = { Text(text = "جستجو") },
                )
                BottomNavigationItem(
                    selected = bottom_navigation_switch_number == "profile",
                    onClick = {
                        bottom_navigation_switch_number = "profile"
                    },
                    icon = { Icon(imageVector = Icons.Default.Person, contentDescription = "") },
                    label = { Text(text = "محیط کاربری") },
                )

            }
        }
    )
}

@Composable
private fun UserAuthScreenLoginRegisterProfile(
    m_context: Context,
    homeScreenViewModel: HomeScreenViewModel,
    navController: NavController,
    loginLogoutUser:(bottom:String)->Unit
) {
    var is_login by remember {
        mutableStateOf(
            homeScreenViewModel.getUserHasLogin(m_context)
        )
    }
    Column(Modifier.fillMaxSize()) {
        if (is_login) {
            UserProfileScreen(m_context, homeScreenViewModel, navController ){
                loginLogoutUser(it)
            }

        }//user has a login token
        else {
            LoginRegisterUserScreenSwitch(m_context, homeScreenViewModel){
                loginLogoutUser(it)
            }

        }//show login register page
    }
}

@Composable
fun drawerContentScaffold(m_context: Context,navController: NavController,
                          homeScreenViewModel: HomeScreenViewModel) {

    var is_login by remember {
        mutableStateOf(
            homeScreenViewModel.getUserHasLogin(m_context)
        )
    }
    val user_login_data=homeScreenViewModel.getUserLoginData(m_context)

    val drawerContentColumnScroll = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(drawerContentColumnScroll)
    ) {
        if (is_login) {
            Box(
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .height(90.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                        },
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "",
                        modifier = Modifier.size(60.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = user_login_data.name, fontSize = 20.sp)
                }
            }
        }

        //menu items
        drawerContentScaffoldMenuItem("خانه",Icons.Default.Home){

        }
        drawerContentScaffoldMenuItem("دسته بندی ها ",Icons.Default.Home){
            navController.navigate("category")
        }
        if (is_login) {
            drawerContentScaffoldMenuItem("دوره های من", Icons.Default.Person){
                navController.navigate("myCourses")

            }
            drawerContentScaffoldMenuItem("دوره های موردعلاقه", Icons.Default.Favorite){
                navController.navigate("myFavouriteCourses")
            }
        }
        drawerContentScaffoldMenuItem("درباره ما",Icons.Default.AccountBox){

        }
        drawerContentScaffoldMenuItem("حریم خصوصی",Icons.Default.AccountBox){

        }
        drawerContentScaffoldMenuItem("خروج",Icons.Default.ExitToApp){
            //work on this a little bit dose not work right know
            ActivityCompat.finishAffinity(m_context as Activity)
        }


    }
}

@Composable
fun drawerContentScaffoldMenuItem(title:String,icon:ImageVector,onClick:()->Unit){

    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp)
            .clickable {
                onClick()
            }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "",
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = title, color = Color.DarkGray, fontSize = 18.sp)
    }//menu icon
}