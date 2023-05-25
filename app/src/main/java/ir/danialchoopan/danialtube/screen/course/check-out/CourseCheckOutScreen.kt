package ir.danialchoopan.danialtube.screen.course.`check-out`

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import ir.danialchoopan.danialtube.data.api.RequestEndPoints
import ir.danialchoopan.danialtube.data.api.model.courseShow.CourseWithVideosUser
import ir.danialchoopan.danialtube.data.api.model.courseShow.ShowCourse
import ir.danialchoopan.danialtube.data.api.requests.course.CourseRequest
import ir.danialchoopan.danialtube.data.api.requests.user.UserAuthRequest
import ir.danialchoopan.danialtube.screen.user.CountdownTimer2Min
import ir.danialchoopan.danialtube.ui.componets.DialogBoxLoading
import ir.danialchoopan.danialtube.utils.formatPrice

@Composable
fun CourseCheckOutScreen(navController: NavController, course_id: String) {
    //context
    val m_context = LocalContext.current

    var onGoingProgress by remember {
        mutableStateOf(true)
    }

    if (onGoingProgress) {
        DialogBoxLoading()
    }

    val user_id = m_context.getSharedPreferences("user_data", Context.MODE_PRIVATE).getInt("id", 0)


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

            var requestMessage by remember {
                mutableStateOf("")
            }

            var courseDataShow by remember { mutableStateOf<ShowCourse?>(null) }
            val courseRequestVolley = CourseRequest(LocalContext.current)
            courseRequestVolley.showCourse(course_id) {
                courseDataShow = it
                onGoingProgress = false
            }

            val verticalScroll = rememberScrollState()
            if (courseDataShow != null) {


                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(verticalScroll)
                        .padding(15.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Spacer(modifier = Modifier.height(25.dp))
                    Text(text = "صورت حساب", fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "دوره آموزشی : ${courseDataShow?.courseWithVideosUser!!.nameTitle}",
                        fontSize = 16.sp,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "مالیات ارزش افزوده : +9%", fontSize = 16.sp)
                    val finalPrice =
                        formatPrice(courseDataShow!!.courseWithVideosUser.price.toString())
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "مجموع مبلع قابل پرداخت  : $finalPrice",
                        fontSize = 16.sp,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            navController.navigate("home") {
                                popUpTo(0)
                            }
                            m_context.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse(RequestEndPoints.transactionUrl + course_id + "/" + user_id)
                                )
                            )
                        }) {
                        Text(text = "پرداخت", fontSize = 18.sp)
                    }


                }
            } else {
                DialogBoxLoading()
            }
        }
    )
}