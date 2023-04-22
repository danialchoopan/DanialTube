package ir.danialchoopan.danialtube.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.log


@Composable
fun LoginRegisterUserScreenSwitch() {
    var login_Screen_switch by remember {
        mutableStateOf(true)
    }
    if (login_Screen_switch) {
        LoginUserProfileScreen { login ->
            login_Screen_switch = login
        }
    } else {
        RegisterUserProfileScreen { login ->
            login_Screen_switch = login
        }
    }
}

@Composable
fun LoginUserProfileScreen(onButtonLoginSwitchClick: (login: Boolean) -> Unit) {

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
                    onButtonLoginSwitchClick(false)
                }, color = Color.Blue
        )

    }
}


@Composable
fun RegisterUserProfileScreen(onButtonLoginSwitchClick: (login: Boolean) -> Unit) {
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
                    onButtonLoginSwitchClick(true)

                }, color = Color.Blue
        )

    }
}