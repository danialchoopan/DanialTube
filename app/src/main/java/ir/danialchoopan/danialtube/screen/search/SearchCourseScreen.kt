package ir.danialchoopan.danialtube.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ir.danialchoopan.danialtube.data.api.model.courseSearch.SearchCourseModel
import ir.danialchoopan.danialtube.data.api.model.courseShow.ShowCourse
import ir.danialchoopan.danialtube.data.api.requests.course.CourseRequest
import ir.danialchoopan.danialtube.ui.componets.DialogBoxLoading
import ir.danialchoopan.danialtube.ui.componets.courseCardShowComponent

@Composable
fun searchCourseScreen(navController: NavController) {
    //text editors
    var txt_search_course by remember {
        mutableStateOf("")
    }

    //process
    var onGoingProgress by remember {
        mutableStateOf(false)
    }

    //end load data new way
    var courseDataSearch by remember { mutableStateOf<SearchCourseModel?>(null) }

    val CourseRequestVolley = CourseRequest(LocalContext.current)
    LaunchedEffect(Unit) {
        if (txt_search_course != "" && txt_search_course.length > 3) {
            onGoingProgress=true

            CourseRequestVolley.searchCourse(txt_search_course) { searchCourse ->
                courseDataSearch = searchCourse
                onGoingProgress=false

            }
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(Color.LightGray)
    ) {
        Column {
            OutlinedTextField(
                value = txt_search_course,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    txt_search_course = it
                        onGoingProgress=true
                        CourseRequestVolley.searchCourse(txt_search_course) { searchCourse ->
                            onGoingProgress=false

                            courseDataSearch = searchCourse
                        }

                },
                label = { Text(text = "جستجو ...") },
                isError = false,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = ""
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {

                    }
                )
            )


            if (onGoingProgress) {
                Column(


                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(modifier = Modifier.size(100.dp))
                    Text(text = " ... در حال بارگذاری", color = Color.DarkGray, fontSize = 14.sp)
                    LinearProgressIndicator()

                }
            }
            if (courseDataSearch != null) {

                if (courseDataSearch?.courseWithVideosUserSearch!!.isEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(text = "نتیجه ای پیدا نشد :-(", color = Color.DarkGray, fontSize = 14.sp)
                        Text(text = "برای جستجو نام دوره را وارد کنید", color = Color.DarkGray, fontSize = 14.sp)
                    }
                }else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(), content = {
                            items(courseDataSearch!!.courseWithVideosUserSearch) { course ->
                                courseCardShowComponent(
                                    thumbnail = course.thumbnail,
                                    nameTitle = course.nameTitle,
                                    categoryName = course.subCourseCategories.name,
                                    teacherName = course.user.name,
                                    coursePrice = course.price.toString(),
                                    onClick = {
                                        navController.navigate("course/${course.id}")
                                    },
                                    modifierCard = Modifier.fillMaxWidth(),
                                    modifierImg = Modifier.fillMaxWidth().height(200.dp)
                                )
                            }
                        })
                }

                if(txt_search_course==""){
                    courseDataSearch?.courseWithVideosUserSearch= emptyList()
                    onGoingProgress=false
                }
            } else
            {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(text = "نتیجه ای پیدا نشد :-(", color = Color.DarkGray, fontSize = 14.sp)
                    Text(text = "برای جستجو نام دوره را وارد کنید", color = Color.DarkGray, fontSize = 14.sp)
                }
            }





        }


    }
}