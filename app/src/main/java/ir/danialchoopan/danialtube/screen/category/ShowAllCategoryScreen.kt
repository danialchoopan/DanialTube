package ir.danialchoopan.danialtube.screen.category

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
import ir.danialchoopan.danialtube.data.api.model.showCategory.CourseCategory
import ir.danialchoopan.danialtube.data.api.model.showCategory.ShowCategory
import ir.danialchoopan.danialtube.data.api.requests.category.CourseCategoryRequest
import ir.danialchoopan.danialtube.data.api.requests.course.CourseRequest
import ir.danialchoopan.danialtube.ui.componets.DialogBoxLoading
import ir.danialchoopan.danialtube.ui.componets.cardCategory
import ir.danialchoopan.danialtube.ui.componets.courseCardShowComponentGrid2

@Composable
fun ShowAllCategoryScreen(navController: NavController){

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
                title = { Text("دسته بندی ها") })
        },
        content = {
            //end load data new way
            var course_category by remember { mutableStateOf<ShowCategory?>(null) }

            val courseCategoryRequestVolley = CourseCategoryRequest(LocalContext.current)
            LaunchedEffect(Unit) {
                courseCategoryRequestVolley.getCategories {
                    course_category = it
                }
            }


            if (course_category != null) {
                val categoreis = course_category!!.courseCategories
                if (categoreis.isNotEmpty()) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(), content = {
                            items(categoreis) { category ->
                                cardCategory(category.name,category.icon) {
                                    //on Click
                                    navController.navigate("subCategory/${category.id}")
                                }
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
                            text = "دسته بندی ها خالی است  ",
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