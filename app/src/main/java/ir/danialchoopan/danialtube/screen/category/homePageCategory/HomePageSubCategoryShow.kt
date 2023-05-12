package ir.danialchoopan.danialtube.screen.category.homePageCategory

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
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
import ir.danialchoopan.danialtube.data.api.model.courseShowCategory.ShowCategoriesCourse
import ir.danialchoopan.danialtube.data.api.model.showSubCategory.ShowSubCategory
import ir.danialchoopan.danialtube.data.api.requests.category.CourseCategoryRequest
import ir.danialchoopan.danialtube.ui.componets.*

@Composable
fun homePageCategoryScreen(navController: NavController, category_id: String) {

    var sub_category_id_show by remember {
        mutableStateOf(0)
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
                title = { Text("زیر دسته بندی ها") })
        },
        content = {
            //end load data new way
            var course_category by remember { mutableStateOf<ShowSubCategory?>(null) }

            val courseCategoryRequestVolley = CourseCategoryRequest(LocalContext.current)
            var courseSubCategories by remember { mutableStateOf<ShowCategoriesCourse?>(null) }
            LaunchedEffect(Unit) {
                courseCategoryRequestVolley.getSubCategories(category_id) {
                    course_category = it
                    sub_category_id_show = course_category!!.subCourseCategories[0].id
                }

                courseCategoryRequestVolley.getSubCategoryCourse(sub_category_id_show.toString()) {
                    courseSubCategories = it
                }

            }



            if (course_category != null) {
                val categoreis = course_category!!.subCourseCategories
                if (categoreis.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(5.dp))
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp), content = {
                            items(categoreis) { category ->
                                cardCategoryRow(category.name, category.icon) {
                                    //on Click
                                    sub_category_id_show = category.id
                                }
                            }
                        }
                    )

                    LazyColumn(content = {
                        items(courseSubCategories!!.subCourseCategoriesCourses) { course ->
                            courseCardShowListComponent(
                                thumbnail = course.thumbnail,
                                nameTitle = course.nameTitle,
                                categoryName = course.subCourseCategories.name,
                                teacherName = course.user.name,
                                coursePrice = course.price.toString(),
                                onClick = {
                                    navController.navigate("course/${course.id}")
                                }
                            )
                        }
                    })


                } else {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "زیر دسته بندی ای تعریف نشده است :-(",
                            color = Color.Gray,
                            fontSize = 19.sp
                        )
                    }
                }
            } else {
                LinerLoaderComponent()
            }
        }
    )

}