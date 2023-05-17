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
import ir.danialchoopan.danialtube.utils.isValidPhoneNumber
import kotlinx.coroutines.delay

@Composable
fun ForgotPasswordPhoneCheckScreen(navController: NavController) {
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
                title = { Text("برسی شماره همراه") })
        },
        content = {

            var onGoingProgress by remember {
                mutableStateOf(false)
            }

            if (onGoingProgress) {
                DialogBoxLoading()
            }

            var phoneNumberCheckTextEdit by remember {
                mutableStateOf("")
            }

            var phoneNumberCheckTextEditError by remember {
                mutableStateOf(false)
            }

            var requestMessage by remember {
                mutableStateOf("")
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

                val userPhoneNumber = ""
                Text(
                    text = "برسی شماره همراه ، شماره همراهی که با ان نام نویسی کرده اید را وارد کنید",
                    color = Color.Gray
                )
                Text(
                    text = requestMessage,
                    color = Color.Red
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = phoneNumberCheckTextEdit,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {
                        phoneNumberCheckTextEdit = it
                        phoneNumberCheckTextEditError = false
                    },
                    leadingIcon =
                    {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = ""
                        )
                    },
                    isError = phoneNumberCheckTextEditError,
                    label = { Text(text = "شماره همراه") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        modifier = Modifier.width(100.dp),
                        onClick = {
                            onGoingProgress = true
                            phoneNumberCheckTextEditError =
                                phoneNumberCheckTextEdit.isEmpty()
                            phoneNumberCheckTextEditError =
                                !isValidPhoneNumber(phoneNumberCheckTextEdit)
                            if (phoneNumberCheckTextEdit.isNotEmpty() &&
                                isValidPhoneNumber(phoneNumberCheckTextEdit)
                            ) {
                                userAuthRequest.userRestPasswordCheckPhone(
                                    phoneNumberCheckTextEdit
                                ) {
                                    onGoingProgress = false
                                    if (it) {
                                        navController.navigate("forgotPasswordPhoneVerify/$phoneNumberCheckTextEdit")
                                    } else {
                                        requestMessage = "شماره وارد شده معتبر نیست "
                                    }
                                }
                            }else{
                                onGoingProgress = false
                            }
                        }) {

                        Text(text = "ارسال", fontSize = 16.sp)
                    }

                }


                Spacer(modifier = Modifier.height(350.dp))

            }
        }
    )
}