package ir.danialchoopan.danialtube.screen.course.`check-out`

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
import ir.danialchoopan.danialtube.data.api.model.courseShow.CourseWithVideosUser
import ir.danialchoopan.danialtube.data.api.requests.user.UserAuthRequest
import ir.danialchoopan.danialtube.screen.user.CountdownTimer2Min
import ir.danialchoopan.danialtube.ui.componets.DialogBoxLoading

@Composable
fun CourseCheckOutScreen(navController: NavController,course_id:String){
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
                title = { Text("شرکت در دوره") })
        },
        content = {

            var onGoingProgress by remember {
                mutableStateOf(false)
            }

            if (onGoingProgress) {
                DialogBoxLoading()
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

                Text(text = "صورت حساب")
                Text(text = "دوره آموزشی : ")
                Text(text = "مالیات ارزش افزوده : +9%")
                Text(text = "مجموع مبلع قابل پرداخت  : ")
                Text(text = "درگاه های پرداختی ")

                Button(onClick = { }) {
                    Text(text = "پرداخت")
                }


            }
        }
    )
}