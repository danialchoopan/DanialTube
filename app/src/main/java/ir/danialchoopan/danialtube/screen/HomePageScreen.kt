package ir.danialchoopan.danialtube.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ir.danialchoopan.danialtube.R
import ir.danialchoopan.danialtube.viewmodels.HomeScreenViewModel
import kotlinx.coroutines.launch


@Composable
fun HomePageScreen() {
    val homeScreenViewModel: HomeScreenViewModel= viewModel()
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
                            Spacer(modifier = Modifier.width(5.dp))
                            for (i in 1..10) {
                                Card(elevation = 2.dp, modifier = Modifier.width(110.dp)) {
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
                                        .width(250.dp)
                                        .padding(5.dp)
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
                                        .width(250.dp)
                                        .padding(5.dp)
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
                                                .background(Color.Green)
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
                        Spacer(modifier = Modifier.height(80.dp))
                    }
                }//profile
                "search" -> {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(text = "baka mitai")
                    }
                }//search
                "profile" -> {

                    //user auth
                    UserAuthScreenLoginRegister()
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
private fun UserAuthScreenLoginRegister() {
    var is_login by remember {
        mutableStateOf(true)
    }
    Column(Modifier.fillMaxSize()) {
        if (is_login) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(text = "ورود به حساب کاربری ", fontSize = 18.sp)
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = "",
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { },
                    label = { Text(text = "شماره همراه - پست الکترونیک") },
                    isError = false,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { }
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = "",
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { },
                    label = { Text(text = "رمزعبور") },
                    isError = false,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { }
                    )
                )

                Spacer(modifier = Modifier.height(15.dp))
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(), onClick = { }) {
                    Text(text = "ورود به حساب کاربری", modifier = Modifier.padding(10.dp))
                }
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "حساب کاربری ندارید همین حالا نام نویسی کنید ", fontSize = 16.sp,
                    modifier = Modifier
                        .padding(15.dp)
                        .clickable {
                            is_login = false

                        }, color = Color.Blue
                )

            }


        }//login
        else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(text = "نام نویسی", fontSize = 18.sp)
                OutlinedTextField(
                    value = "",
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { },
                    label = { Text(text = "نام نمایشی") },
                    isError = false,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { }
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    value = "",
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { },
                    label = { Text(text = "پست الکترونیک") },
                    isError = false,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { }
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    value = "",
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { },
                    label = { Text(text = "شماره همراه") },
                    isError = false,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { }
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    value = "",
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { },
                    label = { Text(text = "رمزعبور") },
                    isError = false,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { }
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    value = "",
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { },
                    label = { Text(text = "تکرار رمزعبور") },
                    isError = false,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { }
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(), onClick = { }) {
                    Text(text = "نام نویسی", modifier = Modifier.padding(10.dp))
                }
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "ورود به حساب کاربری ", fontSize = 16.sp,
                    modifier = Modifier
                        .padding(15.dp)
                        .clickable {
                            is_login = true

                        }, color = Color.Blue
                )

            }

        }//register
    }
}
