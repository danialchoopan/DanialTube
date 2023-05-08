package ir.danialchoopan.danialtube.screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.accompanist.pager.ExperimentalPagerApi
import ir.danialchoopan.danialtube.data.api.model.CourseCardShow
import ir.danialchoopan.danialtube.data.api.model.homepage.CoursesWithTeacherMostPopular
import ir.danialchoopan.danialtube.data.api.model.homepage.HomePageDataModelRequest
import ir.danialchoopan.danialtube.data.api.requests.HomePageRequest
import ir.danialchoopan.danialtube.ui.componets.AutoSlidingCarousel
import ir.danialchoopan.danialtube.ui.componets.DialogBoxLoading
import ir.danialchoopan.danialtube.ui.componets.courseCardShowComponent
import ir.danialchoopan.danialtube.viewmodels.HomeScreenViewModel
import ir.danialchoopan.utils.LoadImageFormURLFixutils


@OptIn(ExperimentalPagerApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun HomePageScreenScaffoldContent(
    m_context: Context, homeScreenViewModel: HomeScreenViewModel,
    onCourseClick:(courseId:Int)->Unit,
//    onMoreCategoryClick: () -> Unit
//    onMoreCourseClick: () -> Unit
) {

    var onGoingProgress by remember {
        mutableStateOf(true)
    }

    if (onGoingProgress) {
        DialogBoxLoading()
    }

    //end load data new way
    var homePageDataShow by remember { mutableStateOf<HomePageDataModelRequest?>(null) }

    val homePageRequestVolley = HomePageRequest(LocalContext.current)
    LaunchedEffect(Unit) {
        homePageRequestVolley.homePage { success, homePageData ->
            homePageDataShow = homePageData
        }
    }
    if (homePageDataShow != null) {

        onGoingProgress = false
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
//            Row(
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier.fillMaxWidth().padding(15.dp)
//            ) {
//                  Text(text = "بیشتر ...", color = Color.Blue)
//            }
            Text(
                text = "درسته بندی ها",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )

            //category

            LazyRow(
                modifier = Modifier.fillMaxWidth(), content = {
                    items(items = homePageDataShow?.coursesCategory!!) { categoryItem ->
                        Spacer(modifier = Modifier.width(5.dp))
                        Card(
                            elevation = 2.dp, modifier = Modifier
                                .width(110.dp)
                                .height(150.dp)
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
                Text(text = "محبوب ترین دوره ها")
                Text(text = "بیشتر ...", color = Color.Blue)
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
                                    onCourseClick(course.id)
                                    Log.d("course id show 2324536",course.id.toString())

                                })


                            Spacer(modifier = Modifier.width(5.dp))

                        }
                    })

            }

            Text(text = "دوره منتخب", modifier = Modifier.padding(15.dp))
            val mostPapularCourse = homePageDataShow!!.coursesWithTeacherMostPopular

            courseCardShowComponent(
                mostPapularCourse.thumbnail,
                mostPapularCourse.nameTitle,
                mostPapularCourse.subCourseCategories.name,
                mostPapularCourse.user.name,
                mostPapularCourse.price.toString(),
                {
                    //on click
                    onCourseClick(mostPapularCourse.id)
                },
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
            )





            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Text(text = "پرفروش ترین دوره ها")
                Text(text = "بیشتر ...", color = Color.Blue)
            }

            val bestSellingCourses = homePageDataShow!!.allCoursesWithTeacherBestSelling

            if (bestSellingCourses != null) {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(), content = {

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
                                    onCourseClick(course.id)
                                })
                            Spacer(modifier = Modifier.width(5.dp))

                        }
                    })
            }

            Spacer(modifier = Modifier.height(80.dp))
        }
    } else {
        onGoingProgress = true
    }

}


