package ir.danialchoopan.danialtube.screen

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import ir.danialchoopan.danialtube.viewmodels.HomeScreenViewModel
import kotlinx.coroutines.launch


@Composable
fun HomePageScreen() {
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
        drawerContent = { Text(text = "drawerContent") },
        content = {
            when (bottom_navigation_switch_number) {
                "home" -> {
                    HomePageScreenScaffoldContent()
                }//profile
                "search" -> {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(text = "baka mitai")
                    }
                }//search
                "profile" -> {

                    //user auth
                    UserAuthScreenLoginRegisterProfile(m_context, homeScreenViewModel)
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
    homeScreenViewModel: HomeScreenViewModel
) {
    var is_login by remember {
        mutableStateOf(
            homeScreenViewModel.getUserHasLogin(m_context)
        )
    }
    Column(Modifier.fillMaxSize()) {
        if (is_login) {
            LoginRegisterUserScreenSwitch(m_context,homeScreenViewModel)
        }//login - register
        else {
            UserProfileScreen()
        }//user profile
    }
}
