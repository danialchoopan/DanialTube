package ir.danialchoopan.danialtube.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.danialchoopan.danialtube.ui.componets.DialogBoxLoading
import ir.danialchoopan.danialtube.viewmodels.HomeScreenViewModel
import kotlin.math.log


@Composable
fun LoginRegisterUserScreenSwitch(
    m_context: Context, homeScreenViewModel: HomeScreenViewModel,
    loginLogoutUser: (bottom: String) -> Unit
) {
    var login_Screen_switch by remember {
        mutableStateOf(true)
    }
    if (login_Screen_switch) {
        LoginUserProfileScreen(m_context, homeScreenViewModel,{ login ->
            login_Screen_switch = login
        },{
            //login success
            loginLogoutUser(it)
        })

    } else {
        RegisterUserProfileScreen(m_context, homeScreenViewModel,{ login ->
            login_Screen_switch = login
        },{
            //register success
            loginLogoutUser(it)
        })
    }
}

@Composable
fun LoginUserProfileScreen(
    m_context: Context,
    homeScreenViewModel: HomeScreenViewModel,
    onButtonLoginSwitchClick: (login: Boolean) -> Unit,
    loginLogoutUser: (bottom: String) -> Unit
) {
    //text box

    var emailOrPhoneLoginTextBox by remember {
        mutableStateOf("")
    }
    var passwordLoginTextBox by remember {
        mutableStateOf("")
    }
    //other variables
    var onGoingProgress by remember {
        mutableStateOf(false)
    }

    if (onGoingProgress) {
        DialogBoxLoading()
    }

    val loginScroll = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .verticalScroll(loginScroll),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "ورود به حساب کاربری ", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = emailOrPhoneLoginTextBox,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                emailOrPhoneLoginTextBox = it
            },
            leadingIcon =
            {
                Icon(
                    imageVector = Icons.Outlined.Email,
                    contentDescription = ""
                )
            },
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
            value = passwordLoginTextBox,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                passwordLoginTextBox = it
            },
            leadingIcon =
            {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = ""
                )
            },
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
        Button(
            modifier = Modifier.fillMaxWidth(), onClick = {

                if (emailOrPhoneLoginTextBox.isNotEmpty() && passwordLoginTextBox.isNotEmpty()) {

                    onGoingProgress = true

                    homeScreenViewModel.userLoginRequest(
                        m_context,
                        emailOrPhoneLoginTextBox,
                        passwordLoginTextBox
                    ) { success, loginResponse ->
                        onGoingProgress = false

                        if (success) {

                            Toast.makeText(
                                m_context,
                                "شما به موفقیت وارد شدید ",
                                Toast.LENGTH_SHORT
                            ).show()
                            Toast.makeText(
                                m_context,
                                "خوش آمدی ${loginResponse.user.name} ",
                                Toast.LENGTH_SHORT
                            ).show()
                            loginLogoutUser("home")
                        } else {
                            Toast.makeText(
                                m_context,
                                "نام کاربری یا رمز عبور اشتباه است  ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }

                }

            }) {

            Text(text = "ورود به حساب کاربری", modifier = Modifier.padding(10.dp), fontSize = 19.sp)
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "حساب کاربری ندارید همین حالا نام نویسی کنید ", fontSize = 16.sp,
            modifier = Modifier
                .padding(15.dp)
                .clickable {
                    onButtonLoginSwitchClick(false)
                }, color = Color.Blue
        )

    }
}


@Composable
fun RegisterUserProfileScreen(
    m_context: Context, homeScreenViewModel: HomeScreenViewModel,
    onButtonLoginSwitchClick: (login: Boolean) -> Unit,
    loginLogoutUser: (bottom: String) -> Unit
) {

    //text box
    var nameRegisterTextBox by remember {
        mutableStateOf("")
    }
    var phoneRegisterTextBox by remember {
        mutableStateOf("")
    }
    var emailRegisterTextBox by remember {
        mutableStateOf("")
    }
    var passwordRegisterTextBox by remember {
        mutableStateOf("")
    }
    var rePasswordRegisterTextBox by remember {
        mutableStateOf("")
    }

    //other variables
    var onGoingProgress by remember {
        mutableStateOf(false)
    }

    if (onGoingProgress) {
        DialogBoxLoading()
    }

    val registerScroll = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .verticalScroll(registerScroll),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "نام نویسی", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = nameRegisterTextBox,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                nameRegisterTextBox = it
            },
            label = { Text(text = "نام نمایشی") },
            leadingIcon =
            {
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = ""
                )
            },
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
            value = emailRegisterTextBox,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                emailRegisterTextBox = it
            },
            label = { Text(text = "پست الکترونیک") },
            leadingIcon =
            {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = ""
                )
            },
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
            value = phoneRegisterTextBox,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                phoneRegisterTextBox = it
            },
            label = { Text(text = "شماره همراه") },
            leadingIcon =
            {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = ""
                )
            },
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
            value = passwordRegisterTextBox,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                passwordRegisterTextBox = it
            },
            label = { Text(text = "رمزعبور") },
            leadingIcon =
            {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = ""
                )
            },
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
            value = rePasswordRegisterTextBox,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                rePasswordRegisterTextBox = it
            },
            leadingIcon =
            {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = ""
                )
            },
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
        Button(
            modifier = Modifier.fillMaxWidth(), onClick = {
                if (nameRegisterTextBox.isNotEmpty() &&
                    emailRegisterTextBox.isNotEmpty() &&
                    phoneRegisterTextBox.isNotEmpty() &&
                    passwordRegisterTextBox.isNotEmpty() &&
                    rePasswordRegisterTextBox.isNotEmpty()
                ) {
                    if (passwordRegisterTextBox == rePasswordRegisterTextBox) {
                        onGoingProgress = true
                        homeScreenViewModel.userRegisterRequest(
                            m_context,
                            nameRegisterTextBox,
                            emailRegisterTextBox,
                            phoneRegisterTextBox,
                            passwordRegisterTextBox
                        ) {success->
                            if (success) {
                                Toast.makeText(m_context, "شما با موفقیت نام نویسی شدید", Toast.LENGTH_SHORT).show()
                                loginLogoutUser("home")
                            } else {
                                Toast.makeText(m_context, "مشکلی پیش آمده است لطفا بعدا امتحان کنید", Toast.LENGTH_SHORT).show()
                            }
                            onGoingProgress = false
                        }
                        //clear text boxs
                        nameRegisterTextBox = ""
                        emailRegisterTextBox = ""
                        phoneRegisterTextBox = ""
                        passwordRegisterTextBox = ""
                        rePasswordRegisterTextBox = ""
                    } else {
                        Toast.makeText(m_context, "not match passwords", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(
                        m_context,
                        "لطفا ورودی های خود را برسی کنید ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }) {
            Text(text = "نام نویسی", modifier = Modifier.padding(10.dp), fontSize = 19.sp)
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "ورود به حساب کاربری ", fontSize = 16.sp,
            modifier = Modifier
                .padding(15.dp)
                .clickable {
                    onButtonLoginSwitchClick(true)

                }, color = Color.Blue
        )

    }
}