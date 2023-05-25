package ir.danialchoopan.danialtube.screen.course.comments

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ir.danialchoopan.danialtube.data.api.model.courseComment.CourseComments
import ir.danialchoopan.danialtube.data.api.model.myCourses.MyCoursesModel
import ir.danialchoopan.danialtube.data.api.requests.course.CourseRequest
import ir.danialchoopan.danialtube.data.api.requests.user.UserAuthRequest
import ir.danialchoopan.danialtube.ui.componets.DialogBoxLoading
import ir.danialchoopan.danialtube.ui.componets.courseCardShowComment
import ir.danialchoopan.danialtube.ui.componets.courseCardShowListComponent
import ir.danialchoopan.danialtube.utils.IsUserLogin

@Composable
fun CourseCommentScreen(navController: NavController, course_id: String) {

    val m_context = LocalContext.current

    var onGoingProgress by remember {
        mutableStateOf(true)
    }

    if (onGoingProgress) {
        DialogBoxLoading()
    }

    var userCommentTextBox by remember {
        mutableStateOf("")
    }

    var userCommentTextBoxError by remember {
        mutableStateOf(false)
    }

    val userDataLoad = m_context.getSharedPreferences("user_data", Context.MODE_PRIVATE)

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
                title = { Text("دیدگاه های دوره ") })
        },
        content = {
            //end load data new way
            var courseComment by remember { mutableStateOf<CourseComments?>(null) }

            val courseRequestVolley = CourseRequest(LocalContext.current)

            LaunchedEffect(Unit) {
                courseRequestVolley.showCourseComment(course_id) {
                    courseComment = it
                }
            }


            if (courseComment != null) {
                Column(modifier = Modifier.fillMaxSize()) {

                    if (courseComment!!.isNotEmpty()) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxHeight(0.9f)
                                .fillMaxHeight(), content = {
                                items(courseComment!!) { comment ->
                                    Card(modifier = Modifier.padding(10.dp)) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(5.dp)
                                        ) {
                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Text(
                                                    text = comment.comment, color = Color.Gray,
                                                    modifier = Modifier.fillMaxWidth(0.7f)
                                                )
                                                if (IsUserLogin(m_context)) {
                                                    if (comment.userId == userDataLoad.getInt(
                                                            "id",
                                                            0
                                                        )
                                                    ) {
                                                        Box(modifier = Modifier.fillMaxWidth(0.3f)) {

                                                            Icon(imageVector = Icons.Default.Delete,
                                                                contentDescription = "",
                                                                tint = Color.Red,
                                                                modifier = Modifier
                                                                    .fillMaxWidth()
                                                                    .clickable {
                                                                        courseRequestVolley.removeCourseComment(
                                                                            comment.id.toString()
                                                                        ) {
                                                                            navController.popBackStack()
                                                                            navController.navigate("course/$course_id/comments")
                                                                        }
                                                                    }
                                                            )
                                                        }

                                                    }
                                                }
                                            }
                                            Text(
                                                text = comment.user.name,
                                                color = Color.LightGray,
                                                fontSize = 12.sp
                                            )
                                        }
                                    }
                                }
                            })
                    } else {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.9f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "دیدگاهی وجود ندارد ",
                                color = Color.Gray,
                                fontSize = 19.sp
                            )
                        }
                    }


                    if(IsUserLogin(m_context)) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            OutlinedButton(
                                modifier = Modifier.fillMaxHeight(), onClick = {
                                    userCommentTextBoxError = userCommentTextBox.isEmpty()
                                    if (userCommentTextBox.isNotEmpty()) {
                                        onGoingProgress = true
                                        courseRequestVolley.addCourseComment(
                                            userCommentTextBox,
                                            course_id
                                        ) {
                                            courseRequestVolley.showCourseComment(course_id) {
                                                courseComment = it
                                                onGoingProgress = false
                                                userCommentTextBox = ""
                                            }
                                        }
                                    }

                                }) {
                                Icon(imageVector = Icons.Default.Send, contentDescription = "")
                            }
                            OutlinedTextField(
                                value = userCommentTextBox,
                                singleLine = true,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .padding(),
                                onValueChange = {
                                    userCommentTextBox = it
                                    userCommentTextBoxError = false
                                },
                                label = { Text(text = "دیدگاه شما ...") },
                                isError = userCommentTextBoxError,
                            )
                        }//end send comment
                    }

                    Spacer(modifier = Modifier.height(50.dp))

                }

                onGoingProgress = false
            }
        }
    )
}