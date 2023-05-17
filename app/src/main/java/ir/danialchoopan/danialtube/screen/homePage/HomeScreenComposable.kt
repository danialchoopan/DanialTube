package ir.danialchoopan.danialtube.screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.accompanist.pager.ExperimentalPagerApi
import ir.danialchoopan.danialtube.R
import ir.danialchoopan.danialtube.data.api.model.homepage.HomePageDataModelRequest
import ir.danialchoopan.danialtube.data.api.requests.homePage.HomePageRequest
import ir.danialchoopan.danialtube.ui.componets.AutoSlidingCarousel
import ir.danialchoopan.danialtube.ui.componets.DialogBoxLoading
import ir.danialchoopan.danialtube.ui.componets.courseCardShowComponent
import ir.danialchoopan.danialtube.ui.componets.courseCardShowMoreComponent
import ir.danialchoopan.danialtube.viewmodels.HomeScreenViewModel
import ir.danialchoopan.danialtube.utils.LoadImageFormURLFixutils
import ir.danialchoopan.danialtube.utils.reloadHomePage


@OptIn(
    ExperimentalPagerApi::class, ExperimentalGlideComposeApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun HomePageScreenScaffoldContent(
    m_context: Context,
    navController: NavController,
    homeScreenViewModel: HomeScreenViewModel,
) {
    var failedStatus by remember {
        mutableStateOf(false)
    }
    //end load data

    var homePageDataShow by remember { mutableStateOf<HomePageDataModelRequest?>(null) }

    val homePageRequestVolley = HomePageRequest(LocalContext.current)
    LaunchedEffect(Unit) {
        homePageRequestVolley.homePage({ success, homePageData ->
            homePageDataShow = homePageData
        }, {
            //failed
            failedStatus = true
        })
    }
    if (homePageDataShow != null) {
        val rememberScrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState)
        ) {

            val sliderHomePagePhotos = homePageDataShow!!.homePageSlider
            Card {
                AutoSlidingCarousel(
                    itemsCount = sliderHomePagePhotos!!.size,
                    itemContent = { index ->
                        GlideImage(
                            model = LoadImageFormURLFixutils(
                                sliderHomePagePhotos[index].photo
                            ),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp),
                        )
                    }
                )
            }

            Text(
                text = "درسته بندی ها",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )

            //category
            LazyRow(
                modifier = Modifier.fillMaxWidth().padding(5.dp), content = {
                    items(items = homePageDataShow?.coursesCategory!!) { categoryItem ->
                        Spacer(modifier = Modifier.width(5.dp))
                        Card(
                            elevation = 2.dp, modifier = Modifier
                                .width(110.dp)
                                .height(150.dp),
                            onClick = {
                                navController.navigate("subCategory/${categoryItem.id}")
                            }
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {

                                GlideImage(
                                    model = LoadImageFormURLFixutils(
                                        categoryItem.icon
                                    ), contentDescription = "",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.height(110.dp)
                                )

                                Column(modifier = Modifier.padding(10.dp)) {
                                    Text(
                                        text = categoryItem.name,
                                        fontSize = 14.sp,
                                        color = Color.DarkGray
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                })

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Text(text = "جدید ترین دوره ها")
                Text(text = "بیشتر ...", color = Color.Blue, modifier = Modifier.clickable {
                    navController.navigate("morePoplarCourse")
                })
            }

            val popularCourses = homePageDataShow!!.allCoursesWithVideosPopular

            if (popularCourses != null) {

                LazyRow(
                    modifier = Modifier.fillMaxWidth(), content = {

                        items(popularCourses) { course ->

                            Spacer(modifier = Modifier.width(5.dp))

                            courseCardShowComponent(
                                course.thumbnail,
                                course.nameTitle,
                                course.subCourseCategories.name,
                                course.user.name,
                                course.price.toString(), {
                                    navController.navigate("course/${course.id}")
                                    Log.d("course id show 2324536", course.id.toString())

                                })


                            Spacer(modifier = Modifier.width(5.dp))

                        }


                        item {
                            courseCardShowMoreComponent(onClick = {
                                navController.navigate("morePoplarCourse")
                            })
                            Spacer(modifier = Modifier.width(15.dp))

                        }
                    })

            }

            Text(text = "دوره منتخب", modifier = Modifier.padding(15.dp))
            val mostPapularCourse = homePageDataShow!!.coursesWithTeacherMostPopular


            courseCardShowComponent(
                thumbnail = mostPapularCourse.thumbnail,
                nameTitle = mostPapularCourse.nameTitle,
                categoryName = mostPapularCourse.subCourseCategories.name,
                teacherName = mostPapularCourse.user.name,
                coursePrice = mostPapularCourse.price.toString(),
                onClick = {
                    navController.navigate("course/${mostPapularCourse.id}")
                },
                modifierCard = Modifier.fillMaxWidth(),
                modifierImg = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Text(text = "محبوب ترین دوره ها")
                Text(text = "بیشتر ...", color = Color.Blue, modifier = Modifier.clickable {
                    navController.navigate("bestSellingCourse")
                })
            }

            val bestSellingCourses = homePageDataShow!!.allCoursesWithTeacherBestSelling

            if (bestSellingCourses != null) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(), content = {

                        items(bestSellingCourses) { course ->

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
                                navController.navigate("bestSellingCourse")
                            })
                            Spacer(modifier = Modifier.width(15.dp))

                        }
                    })
            }

            Spacer(modifier = Modifier.height(80.dp))
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.size(150.dp))
            Text(text = "در حال بارگذاری", color = Color.DarkGray, fontSize = 17.sp)
            Spacer(modifier = Modifier.height(10.dp))
            LinearProgressIndicator()

            Spacer(modifier = Modifier.height(10.dp))

            if (failedStatus) {
                Text(
                    text = "به نظر می آید مشکلی پیش آمده است !",
                    color = Color.Gray,
                    modifier = Modifier.padding(10.dp)
                )
                Column(
                    modifier = Modifier.clickable {
                        reloadHomePage(navController)
                    }
                ) {

                    Text(text = "تلاش دوباره", color = Color.Gray)
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_sync_problem_24),
                        contentDescription = "",
                        modifier = Modifier.size(70.dp)
                    )
                }

            }
        }

//        onGoingProgress = true
    }

}


