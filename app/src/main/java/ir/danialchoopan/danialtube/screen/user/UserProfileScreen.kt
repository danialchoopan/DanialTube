package ir.danialchoopan.danialtube.screen

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.GlideImage
import ir.danialchoopan.danialtube.data.api.model.courseSearch.SearchCourseModel
import ir.danialchoopan.danialtube.data.api.model.myCourses.MyCoursesModel
import ir.danialchoopan.danialtube.data.api.model.myFavouriteCourses.MyFavouriteCoursesModel
import ir.danialchoopan.danialtube.data.api.requests.course.CourseRequest
import ir.danialchoopan.danialtube.data.api.requests.user.UserAuthRequest
import ir.danialchoopan.danialtube.ui.componets.DialogBoxLoading
import ir.danialchoopan.danialtube.ui.componets.courseCardShowComponent
import ir.danialchoopan.danialtube.viewmodels.HomeScreenViewModel
import ir.danialchoopan.danialtube.utils.LoadImageFormURLFixutils
import ir.danialchoopan.danialtube.utils.reloadHomePage


@Composable
fun UserProfileScreen(
    m_context: Context,
    homeScreenViewModel: HomeScreenViewModel,
    navController: NavController
) {

    //process
    var onGoingProgress by remember {
        mutableStateOf(true)
    }

    if (onGoingProgress) {
        DialogBoxLoading()
    }

    val userData = homeScreenViewModel.getUserLoginData(m_context)
    val rememberColumnScroll = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberColumnScroll)
            .padding(5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .padding(5.dp)
                .border(1.dp, Color.Gray)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(vertical = 5.dp, horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "",
                        modifier = Modifier.size(70.dp)
                    )
                    Column() {
                        Text(text = userData.name, fontSize = 18.sp)
                        Text(text = userData.email, fontSize = 14.sp)
                    }
                }

                IconButton(onClick = {
                    onGoingProgress = true
                    homeScreenViewModel.userLogoutRequest(m_context) {
                        onGoingProgress = false
                        reloadHomePage(navController)
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        tint = Color.Red,
                        contentDescription = "",
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp), onClick = {

                    //edit user data
                }) {
                Text(text = "ویرایش حساب کاربری", fontSize = 18.sp)
            }
        }

        var myCoursesModel by remember { mutableStateOf<MyCoursesModel?>(null) }

        val CourseRequestVolley = CourseRequest(LocalContext.current)
        LaunchedEffect(Unit) {
            onGoingProgress = true
            CourseRequestVolley.MyCourses {
                myCoursesModel = it
                onGoingProgress = false
            }

        }

        if (myCoursesModel != null) {

            if (myCoursesModel!!.isNotEmpty()) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    Text(text = "دوره های شرکت شده")

                    Text(text = "بیشتر ...", color = Color.Blue, modifier = Modifier.clickable {
                        navController.navigate("myFavouriteCourses")
                    })


                }
            } else {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp), text = "دوره های شرکت شده"
                )
            }

            if (myCoursesModel!!.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "دوره ای برای نمایش وجود ندارد",
                        color = Color.DarkGray,
                        fontSize = 15.sp
                    )
                }
            }
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(), content = {

                    items(myCoursesModel!!) { course ->

                        Spacer(modifier = Modifier.width(5.dp))
                        //card her
                        courseCardShowComponent(
                            course.course.thumbnail,
                            course.course.nameTitle,
                            course.course.subCourseCategories.name,
                            course.course.user.name,
                            course.course.price.toString(),
                            {
                                //on Click
                                navController.navigate("course/${course.id}")
                            })
                        Spacer(modifier = Modifier.width(5.dp))

                    }
                })
        }

        var myFavouriteCoursesModel by remember { mutableStateOf<MyFavouriteCoursesModel?>(null) }
        LaunchedEffect(Unit) {
            onGoingProgress = true
            CourseRequestVolley.MyFavoritesCourses {
                myFavouriteCoursesModel = it
                onGoingProgress = false

            }
        }


        if (myFavouriteCoursesModel != null) {

            if (myFavouriteCoursesModel!!.isNotEmpty()) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    Text(text = "علاقه مندی ها")

                    Text(text = "بیشتر ...", color = Color.Blue, modifier = Modifier.clickable {
                        navController.navigate("myFavouriteCourses")
                    })
                }
            }else{
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp), text = "علاقه مندی ها"
                )
            }
            if (myFavouriteCoursesModel!!.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "دوره ای برای نمایش وجود ندارد",
                        color = Color.DarkGray,
                        fontSize = 15.sp
                    )
                }
            }
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(), content = {

                    items(myFavouriteCoursesModel!!) { course ->

                        Spacer(modifier = Modifier.width(5.dp))
                        //card her
                        courseCardShowComponent(
                            course.course.thumbnail,
                            course.course.nameTitle,
                            course.course.subCourseCategories.name,
                            course.course.user.name,
                            course.course.price.toString(),
                            {
                                //on Click
                                navController.navigate("course/${course.id}")
                            })
                        Spacer(modifier = Modifier.width(5.dp))

                    }
                })

        }

        Spacer(modifier = Modifier.height(80.dp))
    }
}