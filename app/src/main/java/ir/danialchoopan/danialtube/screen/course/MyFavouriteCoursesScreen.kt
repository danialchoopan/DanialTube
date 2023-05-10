package ir.danialchoopan.danialtube.screen.course

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ir.danialchoopan.danialtube.data.api.model.myFavouriteCourses.MyFavouriteCoursesModel
import ir.danialchoopan.danialtube.data.api.requests.course.CourseRequest
import ir.danialchoopan.danialtube.ui.componets.DialogBoxLoading
import ir.danialchoopan.danialtube.ui.componets.courseCardShowComponentGrid2

@Composable
fun myFavouriteCoursesScreen(navController: NavController){
    //process
    var onGoingProgress by remember {
        mutableStateOf(true)
    }

    if (onGoingProgress) {
        DialogBoxLoading()
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
                title = { Text("دوره های مورد علاقه کاربر") })
        },
        content = {
            //end load data new way
            var myFavouriteCoursesModel by remember { mutableStateOf<MyFavouriteCoursesModel?>(null) }

            val CourseRequestVolley = CourseRequest(LocalContext.current)
            LaunchedEffect(Unit) {
                CourseRequestVolley.MyFavoritesCourses {
                    myFavouriteCoursesModel = it
                }
            }


            if (myFavouriteCoursesModel != null) {
                val courses = myFavouriteCoursesModel!!
                if (myFavouriteCoursesModel!!.isNotEmpty()) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(), content = {
                            items(courses) { course ->
                                courseCardShowComponentGrid2(
                                    thumbnail = course.course.thumbnail,
                                    nameTitle = course.course.nameTitle,
                                    categoryName = course.course.subCourseCategories.name,
                                    teacherName = course.user.name,
                                    coursePrice = course.course.price.toString(),
                                    onClick = {
                                        navController.navigate("course/${course.id}")
                                    },
                                    modifierCard = Modifier.fillMaxWidth(),
                                    modifierImg = Modifier
                                        .fillMaxWidth()
                                        .height(150.dp)
                                )
                            }
                        }
                    )
                } else {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "لیست دوره های مورد علاقه شما خالی است ",
                            color = Color.Gray,
                            fontSize = 19.sp
                        )
                    }
                }
                onGoingProgress = false
            }
        }
    )
}