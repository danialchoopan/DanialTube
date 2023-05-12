package ir.danialchoopan.danialtube.screen.course

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ir.danialchoopan.danialtube.data.api.model.courseShow.ShowCourse
import ir.danialchoopan.danialtube.data.api.requests.course.CourseRequest
import ir.danialchoopan.danialtube.ui.componets.DialogBoxLoading
import ir.danialchoopan.danialtube.utils.LoadImageFormURLFixutils
import ir.danialchoopan.danialtube.utils.bottomBorder
import ir.danialchoopan.danialtube.utils.formatPrice

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterialApi::class)
@Composable
fun showCourseScreen(navController: NavController, course_id: String) {

    //process
    var onGoingProgress by remember {
        mutableStateOf(true)
    }

    if (onGoingProgress) {
        DialogBoxLoading()
    }

    //show videos
    var showCourseVideos by remember {
        mutableStateOf(false)
    }

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
                title = { Text("Danial tub") })
        },
        content = {
            //end load data new way
            var courseDataShow by remember { mutableStateOf<ShowCourse?>(null) }

            val CourseRequestVolley = CourseRequest(LocalContext.current)
            LaunchedEffect(Unit) {
                CourseRequestVolley.showCourse(course_id) { showCourse ->
                    courseDataShow = showCourse
                }
            }
            if (courseDataShow != null) {
                val course = courseDataShow!!.courseWithVideosUser
                val columnVerticalScroll= rememberScrollState()
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(columnVerticalScroll)) {

                    Card {
                        GlideImage(
                            model = LoadImageFormURLFixutils(
                                course.thumbnail
                            ),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(230.dp),
                        )
                    }

                    Text(
                        text = course.nameTitle,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(horizontal = 15.dp, vertical = 6.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val priceCourse = formatPrice(course.price.toString()) + " تومان "
                        Text(
                            text = priceCourse, color = Color(0xFF2E7D32),
                            fontSize = 20.sp,
                            modifier = Modifier.padding(horizontal = 15.dp, vertical = 2.dp)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp, horizontal = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        GlideImage(
                            model = LoadImageFormURLFixutils(
                                course.user.avatar
                            ),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape),
                        )

                        Text(
                            text = course.user.name,
                            color = Color.DarkGray,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 20.dp)
                        )

                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp)
                    ) {

                        IconButton(onClick = { }, modifier = Modifier.width(50.dp)) {
                            Icon(
                                imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = "",
                                tint = Color.Red,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .scale(2f),
                            )
                        }

                        Button(
                            onClick = {

                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 5.dp, vertical = 10.dp)
                        ) {
                            Text(
                                text = "شرکت در دوره", modifier = Modifier
                                    .padding(5.dp)
                                    .scale(1.5f)
                            )
                        }

                    }

                    Column(
                        modifier = Modifier
                            .padding(1.dp)
                            .padding(horizontal = 12.dp, vertical = 5.dp)
                    ) {
                        Text(
                            text = "توضیحات", modifier = Modifier
                                .fillMaxWidth()
                                .bottomBorder(1.dp, Color.Gray), fontSize = 20.sp
                        )
                        Text(
                            text = course.description,
                            modifier = Modifier.padding(5.dp),
                            color = Color.DarkGray
                        )
                    }


                    Column(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .fillMaxWidth()
                            .border(1.dp, Color.Gray, RectangleShape)
                    ) {

                        Box(

                            modifier = Modifier
                                .bottomBorder(1.dp, Color.Gray)
                                .clickable {
                                    showCourseVideos = !showCourseVideos
                                },
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "ویدیو های آموزشی ",
                                    modifier = Modifier
                                        .padding(15.dp)
                                )

                                if (showCourseVideos) {
                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowUp,
                                        contentDescription = "",
                                        modifier = Modifier
                                            .padding(15.dp)
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowDown,
                                        contentDescription = "",
                                        modifier = Modifier
                                            .padding(15.dp)
                                    )
                                }

                            }
                        }//card

                        LazyRow(content = {
                            items(course.videos) { video ->
                                if (showCourseVideos) {
                                    Row(
                                        modifier = Modifier
                                            .border(1.dp, Color.Gray, RectangleShape)

//                                    .padding(horizontal = 10.dp, vertical = 15.dp)
                                            .fillMaxWidth()
                                            .padding(10.dp)
                                        ,
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {

                                        GlideImage(
                                            model = LoadImageFormURLFixutils(
                                                video.thumbnail
                                            ),
                                            contentDescription = "",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .width(150.dp)
                                                .height(100.dp)
                                                .padding(10.dp),
                                        )

                                        Text(text = video.title)
                                        Icon(
                                            imageVector = Icons.Default.Lock,
                                            contentDescription = "",
                                            modifier = Modifier
                                                .padding(15.dp)
                                            ,
                                            tint = Color.Red
                                        )

                                    }
                                }
                            }
                        })

                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Column(
                        modifier = Modifier
                            .padding(1.dp)
                            .padding(horizontal = 12.dp, vertical = 5.dp)
                    ) {
                        Text(
                            text = "دیدگاه ها", modifier = Modifier
                                .fillMaxWidth()
                                .bottomBorder(1.dp, Color.Gray), fontSize = 20.sp
                        )
                        Text(
                            text = course.description,
                            modifier = Modifier.padding(5.dp),
                            color = Color.DarkGray
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))


                    Column(
                        modifier = Modifier
                            .padding(1.dp)
                            .padding(horizontal = 12.dp, vertical = 5.dp)
                    ) {
                        Text(
                            text = "دوره های آموزشی بیشتر", modifier = Modifier
                                .fillMaxWidth()
                                .bottomBorder(1.dp, Color.Gray), fontSize = 20.sp
                        )
                        Text(
                            text = course.description,
                            modifier = Modifier.padding(5.dp),
                            color = Color.DarkGray
                        )
                    }






                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                    )
                }



                onGoingProgress = false
            }


        }
    )
}