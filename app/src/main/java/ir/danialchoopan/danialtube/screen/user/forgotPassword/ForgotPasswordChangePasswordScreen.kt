package ir.danialchoopan.danialtube.screen.user.forgotPassword

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ir.danialchoopan.danialtube.data.api.requests.user.UserAuthRequest
import ir.danialchoopan.danialtube.ui.componets.DialogBoxLoading


@Composable
fun ForgotPasswordChangePasswordScreen(
    navController: NavController,
    phone_number: String,
    userToken: String
) {

    //context
    val m_context = LocalContext.current


    val userAuthRequest = UserAuthRequest(m_context)

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack, contentDescription = "",
                            modifier = Modifier.scale(-1f, 1f)
                        )
                    }
                },
                title = { Text("رمزعبور جدید - فراموشی رمزعبور") })
        },
        content = {


            var onGoingProgress by remember {
                mutableStateOf(false)
            }

            if (onGoingProgress) {
                DialogBoxLoading()
            }

            var passwordEditTextBox by remember {
                mutableStateOf("")
            }

            var rePasswordEditTextBox by remember {
                mutableStateOf("")
            }

            var passwordEditTextBoxError by remember {
                mutableStateOf(false)
            }

            var rePasswordEditTextBoxError by remember {
                mutableStateOf(false)
            }


            val verticalScroll = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(verticalScroll)
                    .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "هویت شما با موفقیت تایید شده است لطفا رمز عبور جدید برای خود انتخاب کنید",
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(10.dp))
                //password
                OutlinedTextField(
                    value = passwordEditTextBox,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {
                        passwordEditTextBox = it
                        passwordEditTextBoxError = false
                    },
                    leadingIcon =
                    {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = ""
                        )
                    },
                    label = { Text(text = " رمز عبور جدید") },
                    isError = passwordEditTextBoxError,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation()
                ) //end text edit


                //password re
                OutlinedTextField(
                    value = rePasswordEditTextBox,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {
                        rePasswordEditTextBox = it
                        rePasswordEditTextBoxError = false
                    },
                    leadingIcon =
                    {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = ""
                        )
                    },
                    label = { Text(text = "تکرار رمز عبور جدید") },
                    isError = rePasswordEditTextBoxError,

                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation()
                )//end text edit
                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(), onClick = {

                        passwordEditTextBoxError = passwordEditTextBox.isEmpty()
                        rePasswordEditTextBoxError = rePasswordEditTextBox.isEmpty()

                        if (
                            passwordEditTextBox.isNotEmpty() &&
                            rePasswordEditTextBox.isNotEmpty()
                        ) {
                            if (passwordEditTextBox == rePasswordEditTextBox) {
                                onGoingProgress = true
                                userAuthRequest.userRestPasswordChangePassword(
                                    passwordEditTextBox,
                                    userToken
                                ) { message ->
                                    onGoingProgress = false

                                    Toast.makeText(
                                        m_context,
                                        message,
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    navController.navigate("home") {
                                        popUpTo(0)
                                    }
                                }


                            } else {
                                Toast.makeText(
                                    m_context,
                                    "رمز عبور انتخابی شما با تکرار آن برار نیست ",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                m_context,
                                "لطفا ورودی های خود را برسی کنید - ورودی خالی مجاز نیست ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }) {
                    Text(
                        text = "بازگشایی رمزعبور",
                        modifier = Modifier.padding(10.dp),
                        fontSize = 19.sp
                    )
                }

                Spacer(modifier = Modifier.height(250.dp))


            }
        }
    )
}