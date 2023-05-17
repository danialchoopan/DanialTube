package ir.danialchoopan.danialtube.screen.user.forgotPassword

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ir.danialchoopan.danialtube.data.api.requests.user.UserAuthRequest
import ir.danialchoopan.danialtube.screen.user.CountdownTimer2Min
import ir.danialchoopan.danialtube.ui.componets.DialogBoxLoading
import kotlinx.coroutines.delay

@Composable
fun ForgotPasswordPhoneVerifyScreen(navController: NavController, phone_number: String) {
    //context
    val m_context = LocalContext.current

    val userAuthRequest = UserAuthRequest(LocalContext.current)

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
                title = { Text("فراموشی رمزعبور - تایید هویت کاربر") })
        },
        content = {

            var onGoingProgress by remember {
                mutableStateOf(false)
            }

            if (onGoingProgress) {
                DialogBoxLoading()
            }

            var phoneNumberValidCodeTextEdit by remember {
                mutableStateOf("")
            }

            var phoneNumberValidCodeTextEditError by remember {
                mutableStateOf(false)
            }

            var sendAgainButtonEnabled by remember {
                mutableStateOf(false)
            }

            var requestMessage by remember {
                mutableStateOf("")
            }

            LaunchedEffect(Unit) {
                userAuthRequest.userRestPasswordRequestSms(phone_number, { message ->
                    requestMessage = message
                }, {
                    requestMessage = "مشکلی پیش آمده است لطفا بعدا دوباره امتحان کنید!"
                })
            }

            val verticalScroll = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(verticalScroll)
                    .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(30.dp))

                val userPhoneNumber = phone_number
                Text(
                    text = "کد تایید برای شما به شماره ($userPhoneNumber) ارسال شده است",
                    color = Color.Gray
                )
                Text(
                    text = requestMessage
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = phoneNumberValidCodeTextEdit,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {
                        phoneNumberValidCodeTextEdit = it
                        phoneNumberValidCodeTextEditError = false
                    },
                    leadingIcon =
                    {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = ""
                        )
                    },
                    isError = phoneNumberValidCodeTextEditError,
                    label = { Text(text = "کد ارسالی") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {

                            userAuthRequest.userRestPasswordRequestSms(phone_number, { message ->
                                requestMessage = message
                            }, {
                                requestMessage = "مشکلی پیش آمده است لطفا بعدا امتحان کنید!"
                            })

                            sendAgainButtonEnabled = false
                        },
                        enabled = sendAgainButtonEnabled
                    ) {

                        Text(text = "ارسال دوباره کد", fontSize = 16.sp)
                    }

                    Button(
                        modifier = Modifier.width(100.dp),
                        onClick = {

                            phoneNumberValidCodeTextEditError =
                                phoneNumberValidCodeTextEdit.isEmpty()
                            if (phoneNumberValidCodeTextEdit.isNotEmpty()) {
                                userAuthRequest.userRestPasswordSendSms(
                                    phone_number,
                                    phoneNumberValidCodeTextEdit,
                                    { success, message, token ->
                                        if (success) {
                                            navController.navigate("forgotPasswordChangePassword/phone/$phone_number/token/$token")
                                            Toast.makeText(m_context, "هویت شما با موفقیت تایید شد!", Toast.LENGTH_SHORT)
                                                .show()
                                        }
                                        requestMessage = message
                                    },
                                    {
                                        requestMessage =
                                            "مشکلی پیش آمده است لطفا بعدا دوباره امتحان کنید کد شما ارسال نشد !"
                                    })
                            }
                        }) {

                        Text(text = "ارسال", fontSize = 16.sp)
                    }

                }
                Spacer(modifier = Modifier.height(10.dp))
                if (!sendAgainButtonEnabled) {
                    CountdownTimer2Min(120000) {
                        sendAgainButtonEnabled = true
                    }
                } else {
                    val countDownTime = " 00:00 "
                    Text(
                        text = "کد ارسالی برای شما تا $countDownTime بعد اعتبار دارد",
                        color = Color.Gray
                    )
                }


                Spacer(modifier = Modifier.height(350.dp))

            }
        }
    )
}
