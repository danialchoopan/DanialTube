package ir.danialchoopan.danialtube.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ir.danialchoopan.danialtube.R
import kotlinx.coroutines.launch


@Composable
fun HomePageScreen() {
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
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_launcher_foreground),
                            contentDescription = "",
                            modifier = Modifier
                                .height(200.dp)
                                .fillMaxWidth()
                                .background(Color.Cyan)
                        )
                        Text(text = "درسته بندی ها", modifier = Modifier.padding(10.dp))
                        //category
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(
                                    rememberScrollState()
                                )
                        ) {
                            Spacer(modifier = Modifier.width(10.dp))
                            for (i in 1..10) {
                                Card(elevation = 2.dp, modifier = Modifier.width(80.dp)) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Image(
                                            painter = painterResource(R.drawable.ic_launcher_foreground),
                                            contentDescription = "",
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .background(Color.Cyan)
                                        )
                                        Column(modifier = Modifier.padding(10.dp)) {
                                            Text(text = "برنامه نویسی ")
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                            }

                        }
                        Text(text = "محبوب ترین دوره ها", modifier = Modifier.padding(10.dp))


                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(
                                    rememberScrollState()
                                )
                        ) {
                            for (i in 1..10) {
                                Card(
                                    elevation = 2.dp, modifier = Modifier
                                        .width(200.dp)
                                        .padding(10.dp)
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Image(
                                            painter = painterResource(R.drawable.ic_launcher_foreground),
                                            contentDescription = "",
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .background(Color.Black)
                                        )
                                        Column(modifier = Modifier.padding(10.dp)) {
                                            Text(text = "آموزش برنامه نویسی اندروید")
                                            Spacer(modifier = Modifier.height(5.dp))
                                            Text(text = "برنامه نویسی ")
                                            Row() {
                                                Text(text = "20 ساعت")
                                                Text(text = "24.0 IRT")
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        Text(text = "دوره منتخب", modifier = Modifier.padding(10.dp))
                        Card(
                            elevation = 2.dp, modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.ic_launcher_foreground),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.Cyan)
                                )
                                Column(modifier = Modifier.padding(10.dp)) {
                                    Text(text = "آموزش برنامه نویسی اندروید")
                                    Spacer(modifier = Modifier.height(5.dp))
                                    Text(text = "برنامه نویسی ")
                                    Row() {
                                        Text(text = "20 ساعت")
                                        Text(text = "24.0 IRT")
                                    }
                                }
                            }
                        }
                        Text(text = "جدید ترین", modifier = Modifier.padding(10.dp))

                        Spacer(modifier = Modifier.height(50.dp))
                    }
                }//profile
                "search" -> {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(text = "baka mitai")
                    }
                }//search
                "profile" -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {

                        TextField(value = "", onValueChange = {

                        }, modifier = Modifier.fillMaxWidth())
                        Spacer(modifier = Modifier.height(10.dp))
                        TextField(value = "", onValueChange = {

                        }, modifier = Modifier.fillMaxWidth())
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(
                            modifier = Modifier.fillMaxWidth(), onClick = { }) {
                            Text(text = "ورود به حساب کاربری")
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(
                            modifier = Modifier.fillMaxWidth(), onClick = { }) {
                            Text(text = "ورود به حساب کاربری")
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "فراموشی رمز عبور ")
                    }
                }//profile
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
