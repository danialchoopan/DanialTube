package ir.danialchoopan.danialtube.screen.splash

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ir.danialchoopan.danialtube.R
import ir.danialchoopan.danialtube.data.api.requests.internetCheck.InternetCheck
import ir.danialchoopan.danialtube.data.api.requests.user.UserAuthRequest
import ir.danialchoopan.danialtube.screen.user.CountdownTimer2Min
import ir.danialchoopan.danialtube.ui.componets.DialogBoxLoading
import ir.danialchoopan.danialtube.ui.theme.Purple500
import ir.danialchoopan.danialtube.utils.reloadHomePage
import kotlinx.coroutines.delay

@Composable
fun splashScreen(navController: NavController) {
    Scaffold(
        topBar = {

        },
        modifier = Modifier.fillMaxSize(),
        content = {
            val m_context = LocalContext.current
            val internetCheck = InternetCheck(m_context)
            val userAuthRequest = UserAuthRequest(m_context)

            val is_login by remember {
                mutableStateOf(
                    m_context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
                        .getString("has_login", "no_login") == "has_login"
                )
            }

            var failedStatus by remember {
                mutableStateOf(false)
            }


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Purple500)
                    .padding(20.dp)
            ) {
                Text(text = "DanialTub", fontSize = 42.sp, color = Color.White)
                Spacer(modifier = Modifier.height(150.dp))
                if (failedStatus) {
                    Text(
                        text = "به نظر می آید مشکلی پیش آمده است !",
                        color = Color.White,
                        modifier = Modifier.padding(10.dp)
                    )
                    Column(
                        modifier = Modifier.clickable {
                            failedStatus = false
                            internetCheck.checkConnection({
                                navController.navigate("home") {
                                    popUpTo(0)
                                }
                                failedStatus = false
                            },
                                //failed
                                {
                                    failedStatus = true
                                }
                            )

                        }
                    ) {

                        Text(text = "تلاش دوباره", color = Color.White)
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_sync_problem_24_white),
                            contentDescription = "",
                            modifier = Modifier.size(70.dp)
                        )
                    }

                } else {
                    Text(text = "درحال برسی ارتباط شما با اینترنت ", color = Color.White)
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.padding(10.dp)
                    )
                }

                internetCheck.checkConnection({
                    if (is_login) {
                        userAuthRequest.checkPhoneNumberIfValid {
                            if (!it) {
                                userAuthRequest.logoutUser {
                                    navController.navigate("home") {
                                        popUpTo(0)
                                    }
                                }
                            }else{
                                navController.navigate("home") {
                                    popUpTo(0)
                                }
                            }
                        }
                    } else {
                        navController.navigate("home") {
                            popUpTo(0)
                        }
                    }
                },
                    //failed
                    {
                        failedStatus = true
                    }
                )
            }
        }
    )
}