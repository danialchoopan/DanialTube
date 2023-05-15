package ir.danialchoopan.danialtube.screen.splash

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
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
fun splashScreen(navController: NavController) {
    Scaffold(
        topBar = {

        },
        modifier = Modifier.fillMaxSize(),
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Cyan)
                    .padding(20.dp)
            ) {
                Text(text = "درحال برسی ارتباط شما با اینترنت ")
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(150.dp))
                LaunchedEffect(Unit) {
                    delay(3000)
//                    navController.navigate("home"){
//                        popUpTo(0)
//                    }
                }
            }
        }
    )
}