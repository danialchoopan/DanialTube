package ir.danialchoopan.danialtube.screen.user

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
import ir.danialchoopan.danialtube.viewmodels.HomeScreenViewModel
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat


@Composable
fun ValidatePhoneNumberScreen(navController: NavController) {

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
                title = { Text("تایید شماره همراه") })
        },
        content = {

            //context
            val m_context = LocalContext.current

            var onGoingProgress by remember {
                mutableStateOf(false)
            }

            if (onGoingProgress) {
                DialogBoxLoading()
            }


            var user_saved_data by remember {
                mutableStateOf(
                    m_context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
                )
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

            val userAuthRequest = UserAuthRequest(LocalContext.current)

            userAuthRequest.requestSmsValidationCode({ message ->
                requestMessage = message
            }, {
                requestMessage = "مشکلی پیش آمده است لطفا بعدا دوباره امتحان کنید!"
            })

            val verticalScroll = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(verticalScroll)
                    .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(30.dp))

                val userPhoneNumber = user_saved_data.getString("phone", "")
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

                            userAuthRequest.requestSmsValidationCode({ message ->
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
                                userAuthRequest.sendRequestSmsValidationCode(
                                    phoneNumberValidCodeTextEdit,
                                    { message, success ->
                                        if (success) {
                                            navController.navigate("home") {
                                                popUpTo(0)
                                            }
                                            Toast.makeText(m_context,message,Toast.LENGTH_SHORT).show()
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

                CountdownTimer2Min {
                    sendAgainButtonEnabled = true
                }


                Spacer(modifier = Modifier.height(350.dp))

            }
        }
    )
}

@Composable
fun CountdownTimer2Min(
    timeLeft: Long = 120000,
    onEnd: () -> Unit
) {
    val context = LocalContext.current
    val timer = remember { mutableStateOf(timeLeft) }

    LaunchedEffect(key1 = timer.value) {
        while (timer.value > 0) {
            delay(1000)
            timer.value -= 1000
        }
        onEnd()
    }
    val realTime = (timer.value / 1000).toInt()
    val minutes = realTime / 60
    val secondsInMinute = realTime % 60
    val countDownTime = String.format("%d:%02d", minutes, secondsInMinute)

    Text(text = "کد ارسالی برای شما تا $countDownTime بعد اعتبار دارد", color = Color.Gray)
}