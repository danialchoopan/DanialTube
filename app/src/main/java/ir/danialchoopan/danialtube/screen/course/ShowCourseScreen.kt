package ir.danialchoopan.danialtube.screen.course

import android.app.Activity
import android.content.pm.ActivityInfo
import android.widget.Toast
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
import ir.danialchoopan.danialtube.data.api.model.courseComment.CourseComments
import ir.danialchoopan.danialtube.data.api.model.courseShow.ShowCourse
import ir.danialchoopan.danialtube.data.api.model.moreCourse.MoreCourseShowCourse
import ir.danialchoopan.danialtube.data.api.requests.course.CourseRequest
import ir.danialchoopan.danialtube.ui.componets.*
import ir.danialchoopan.danialtube.utils.IsUserLogin
import ir.danialchoopan.danialtube.utils.LoadImageFormURLFixutils
import ir.danialchoopan.danialtube.utils.bottomBorder
import ir.danialchoopan.danialtube.utils.formatPrice

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterialApi::class)
@Composable
fun showCourseScreen(navController: NavController, course_id: String) {

    val m_context = LocalContext.current
    val is_user_login = IsUserLogin(m_context)
    val m_activity =m_context as Activity
    m_activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
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

    var processFavorite by remember {
        mutableStateOf(true)
    }

    var courseFavorite by remember {
        mutableStateOf(false)
    }

    var processTake by remember {
        mutableStateOf(true)
    }

    var courseTake by remember {
        mutableStateOf(false)
    }

    var processMoreCourse by remember {
        mutableStateOf(true)
    }

    var processComments by remember {
        mutableStateOf(true)
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
            var moreCourse by remember { mutableStateOf<MoreCourseShowCourse?>(null) }
            var courseComment4 by remember { mutableStateOf<CourseComments?>(null) }

            val courseRequestVolley = CourseRequest(LocalContext.current)
            LaunchedEffect(Unit) {
                processFavorite = true
                courseRequestVolley.showCourse(course_id) { showCourse ->
                    courseDataShow = showCourse
                    courseRequestVolley.CheckFavoriteCourses(course_id) {
                        courseFavorite = it
                        processFavorite = false
                    }
                    courseRequestVolley.CheckCourseTaken(course_id) {
                        courseTake = it
                        processTake = false
                    }
                    courseRequestVolley.ShowMoreCourses {
                        moreCourse = it
                        processMoreCourse = false
                    }
                    courseRequestVolley.showCourseComment4(course_id) {
                        courseComment4 = it
                        processComments = false
                    }
                }
            }
            if (courseDataShow != null) {
                val course = courseDataShow!!.courseWithVideosUser
                val columnVerticalScroll = rememberScrollState()
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(columnVerticalScroll)
                ) {

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
                        if (course.price == 0) {
                            Text(
                                text = "رایگان!", color = Color(0xFF2E7D32),
                                fontSize = 20.sp,
                                modifier = Modifier.padding(horizontal = 15.dp, vertical = 2.dp)
                            )
                        } else {
                            val priceCourse = formatPrice(course.price.toString()) + " تومان "
                            Text(
                                text = priceCourse, color = Color(0xFF2E7D32),
                                fontSize = 20.sp,
                                modifier = Modifier.padding(horizontal = 15.dp, vertical = 2.dp)
                            )
                        }
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
                        if (is_user_login) {
                            if (processFavorite) {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .width(50.dp)
                                        .padding(10.dp)
                                )
                            } else {
                                if (courseFavorite) {
                                    IconButton(onClick = {
                                        processFavorite = true
                                        courseRequestVolley.RemoveFromFavoriteCourses(course_id) {
                                            processFavorite = false
                                            courseFavorite = !courseFavorite
                                        }
                                    }, modifier = Modifier.width(50.dp)) {
                                        Icon(
                                            imageVector = Icons.Default.Favorite,
                                            contentDescription = "",
                                            tint = Color.Red,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .scale(2f),
                                        )
                                    }
                                } else {
                                    IconButton(onClick = {
                                        processFavorite = true
                                        courseRequestVolley.AddToFavoriteCourses(course_id) {
                                            processFavorite = false
                                            courseFavorite = !courseFavorite
                                        }
                                    }, modifier = Modifier.width(50.dp)) {
                                        Icon(
                                            imageVector = Icons.Default.FavoriteBorder,
                                            contentDescription = "",
                                            tint = Color.Red,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .scale(2f),
                                        )
                                    }
                                }
                            }
                        }
                        if (is_user_login) {
                            if (processTake) {
                                LinearProgressIndicator(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 5.dp, vertical = 10.dp)
                                )
                            } else {
                                if (courseTake) {
                                    Button(
                                        onClick = {

                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 5.dp, vertical = 10.dp)
                                            .background(Color.Green)
                                    ) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Box(modifier = Modifier.width(10.dp))
                                            Text(
                                                text = "شما در این دوره شرکت کرده اید",
                                                modifier = Modifier
                                                    .padding(5.dp)
                                                    .scale(1.5f)
                                            )
                                            Icon(
                                                imageVector = Icons.Default.Verified,
                                                contentDescription = "",
                                                tint = Color.White,
                                            )
                                        }
                                    }
                                } else {
                                    Button(
                                        onClick = {
                                            if (is_user_login) {
                                                if (course.price == 0) {
                                                    //take a free course
                                                    courseRequestVolley.TakeFreeCourse(course_id) { message ->
                                                        Toast.makeText(
                                                            m_context,
                                                            message,
                                                            Toast.LENGTH_LONG
                                                        ).show()
                                                        navController.popBackStack()
                                                        navController.navigate("course/$course_id")
                                                    }
                                                } else {
                                                    navController.navigate("course/check-out/$course_id")
                                                }
                                            } else {
                                                Toast.makeText(
                                                    m_context,
                                                    "لطفا اول وارد حساب کاربری خود شوید.",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 5.dp, vertical = 10.dp),

                                        ) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Box(modifier = Modifier.width(10.dp))
                                            Text(
                                                text = "شرکت در دوره", modifier = Modifier
                                                    .padding(5.dp)
                                                    .scale(1.5f)
                                            )
                                            Icon(
                                                imageVector = Icons.Default.AddShoppingCart,
                                                contentDescription = "",
                                                tint = Color.White,
                                            )
                                        }
                                    }
                                }
                                //end else

                            }
                        } else {
                            Button(
                                onClick = {
                                        Toast.makeText(
                                            m_context,
                                            "لطفا اول وارد حساب کاربری خود شوید.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 5.dp, vertical = 10.dp),

                                ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Box(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = "شرکت در دوره", modifier = Modifier
                                            .padding(5.dp)
                                            .scale(1.5f)
                                    )
                                    Icon(
                                        imageVector = Icons.Default.AddShoppingCart,
                                        contentDescription = "",
                                        tint = Color.White,
                                    )
                                }
                            }
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
                                            .fillMaxWidth()
                                            .padding(10.dp)
                                            .clickable {
                                                if(courseTake){
                                                    navController.navigate("course/$course_id/video/${video.id}")
                                                }else{
                                                    Toast.makeText(m_context,"برای مشاهده ی ویدیو های دوره شما باید اول در دوره شرکت کنید ",Toast.LENGTH_SHORT).show()
                                                }
                                            },
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
                                        if (courseTake) {
                                            Icon(
                                                imageVector = Icons.Default.Verified,
                                                contentDescription = "",
                                                modifier = Modifier
                                                    .padding(15.dp),
                                                tint = Color.Green
                                            )
                                        } else {
                                            Icon(
                                                imageVector = Icons.Default.Lock,
                                                contentDescription = "",
                                                modifier = Modifier
                                                    .padding(15.dp),
                                                tint = Color.Red
                                            )
                                        }

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

                        if (courseComment4 != null) {
                            Spacer(modifier = Modifier.height(20.dp))

                            if (courseComment4!!.isNotEmpty()) {
                                LazyRow(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(), content = {
                                        items(courseComment4!!) { course ->

                                            Spacer(modifier = Modifier.width(5.dp))
                                            //card her
                                            var stringComment = ""
                                            if (course.comment.length > 50) {
                                                stringComment =
                                                    course.comment.substring(0, 50) + " ..."
                                            } else {
                                                stringComment = course.comment
                                            }
                                            courseCardShowComment(
                                                comment = stringComment,
                                                username = course.user.name
                                            )
                                            Spacer(modifier = Modifier.width(5.dp))

                                        }
                                        item {
                                            courseCardShowMoreComponent(
                                                "نمایش دیدگاه های بیشتر",
                                                modifierCard = Modifier.height(120.dp), onClick = {
                                                    navController.navigate("course/$course_id/comments")
                                                })
                                            Spacer(modifier = Modifier.width(15.dp))
                                        }
                                    })
                            } else {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(160.dp)
                                ) {
                                    Text(text = "دیدگاهی موجود نیست برای افزودن دیدگاه کلیک کنید",
                                        modifier = Modifier.clickable {
                                            navController.navigate("course/$course_id/comments")
                                        }
                                    )
                                }
                            }
                        } else {
                            LinerLoaderComponent()
                        }


                        //comments
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
                        if (moreCourse != null) {
                            Spacer(modifier = Modifier.height(20.dp))
                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(), content = {
                                    items(moreCourse!!) { course ->

                                        Spacer(modifier = Modifier.width(5.dp))
                                        //card her
                                        courseCardShowComponent(
                                            course.thumbnail,
                                            course.nameTitle,
                                            course.subCourseCategories.name,
                                            course.user.name,
                                            course.price.toString(),
                                            {
                                                //on Click
                                                navController.navigate("course/${course.id}")
                                            })
                                        Spacer(modifier = Modifier.width(5.dp))

                                    }
                                    item {
                                        courseCardShowMoreComponent(onClick = {
                                            navController.popBackStack()
                                            navController.navigate("home")
                                        })
                                        Spacer(modifier = Modifier.width(15.dp))
                                    }
                                })
                        } else {
                            LinerLoaderComponent()
                        }
                    }

                    //scroll down
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