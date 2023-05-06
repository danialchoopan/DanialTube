package ir.danialchoopan.danialtube.screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import ir.danialchoopan.danialtube.R
import ir.danialchoopan.danialtube.data.api.RequestEndPoints
import ir.danialchoopan.danialtube.data.api.model.homepage.HomePageRequestDataModel
import ir.danialchoopan.danialtube.data.api.requests.HomePageRequest
import ir.danialchoopan.danialtube.ui.componets.AutoSlidingCarousel
import ir.danialchoopan.danialtube.ui.componets.DialogBoxLoading
import ir.danialchoopan.danialtube.viewmodels.HomeScreenViewModel
import ir.danialchoopan.utils.LoadImageFormURLFixutils
import kotlinx.coroutines.delay


@OptIn(ExperimentalPagerApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun HomePageScreenScaffoldContent(
    m_context: Context, homeScreenViewModel: HomeScreenViewModel
) {

    var onGoingProgress by remember {
        mutableStateOf(true)
    }

    if (onGoingProgress) {
        DialogBoxLoading()
    }

    //end load data new way
    var homePageData2 by remember { mutableStateOf<HomePageRequestDataModel?>(null) }

    val homePageRequestVolley = HomePageRequest(LocalContext.current)
    LaunchedEffect(Unit) {
        homePageRequestVolley.homePage { success, homePageData ->
            homePageData2 = homePageData
        }
    }
    if (homePageData2 != null) {

        onGoingProgress = false
        val rememberScrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState)
        ) {

            val sliderHomePagePhotos = homePageData2!!.homePageSlider
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
            Text(text = "درسته بندی ها",
                modifier = Modifier.fillMaxWidth().padding(15.dp))

            //category

            LazyRow(
                modifier = Modifier.fillMaxWidth(), content = {
                    items(items = homePageData2?.coursesCategory!!) { categoryItem ->
                        Spacer(modifier = Modifier.width(5.dp))
                        Card(elevation = 2.dp, modifier = Modifier.width(110.dp).height(150.dp)) {
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
                                    Text(text = categoryItem.name, fontSize = 14.sp, color = Color.DarkGray)
                                }
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                })

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(15.dp)
            ) {
                Text(text = "محبوب ترین دوره ها")
                Text(text = "بیشتر ...", color = Color.Blue)
            }

            val popularCourses = homePageData2!!.allCoursesWithVideosPopular

            if (popularCourses != null) {

                LazyRow(
                    modifier = Modifier.fillMaxWidth(), content = {

                        items(popularCourses) { course ->

                            Spacer(modifier = Modifier.width(5.dp))
                            Card(
                                elevation = 2.dp, modifier = Modifier
                                    .width(260.dp)
                                    .padding(5.dp)
                            ) {

                                Column(
                                ) {

                                    GlideImage(
                                        model = LoadImageFormURLFixutils(
                                            course.thumbnail
                                        ),
                                        contentScale = ContentScale.Crop,
                                        contentDescription = "",
                                        modifier = Modifier
                                            .height(150.dp)
                                            .width(260.dp)
                                    )

                                    Column(modifier = Modifier.padding(15.dp)) {
                                        Text(
                                            text = course.nameTitle,
                                            color = Color.DarkGray,
                                            fontSize = 16.sp
                                        )
                                        Spacer(modifier = Modifier.height(5.dp))

                                        Text(
                                            text = "برنامه نویسی اندروید",
                                            color = Color.Gray,
                                            fontSize = 14.sp
                                        )
                                        Row(
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.fillMaxWidth()
                                        ) {

                                            Text(
                                                text = "مدرس : " + course.user.name,
                                                color = Color.Gray,
                                                fontSize = 13.sp
                                            )

                                            val priceCourse = course.price.toString() + " تومان "
                                            Text(text = priceCourse, color = Color(0xFF2E7D32),
                                                fontSize = 14.sp)
                                        }
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.width(5.dp))

                        }
                    })

            }

            Text(text = "دوره منتخب", modifier = Modifier.padding(15.dp))
            val mostPapularCourse = homePageData2!!.allCoursesWithTeacherMostPopular
            Card(
                elevation = 2.dp, modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {

                Column(
                ) {

                    GlideImage(
                        model = LoadImageFormURLFixutils(
                            mostPapularCourse!!.thumbnail
                        ),
                        contentScale = ContentScale.Crop,
                        contentDescription = "",
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                    )

                    Column(modifier = Modifier.padding(15.dp)) {
                        Text(
                            text = mostPapularCourse!!.nameTitle,
                            color = Color.DarkGray,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = "برنامه نویسی اندروید",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Text(
                                text = "مدرس : " + mostPapularCourse!!.user.name,
                                color = Color.Gray,
                                fontSize = 13.sp
                            )

                            val priceCourse = mostPapularCourse!!.price.toString() + " تومان "
                            Text(text = priceCourse, color = Color(0xFF2E7D32),
                                fontSize = 14.sp)
                        }
                    }
                }
            }


            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(15.dp)
            ) {
                Text(text = "پرفروش ترین دوره ها")
                Text(text = "بیشتر ...", color = Color.Blue)
            }

            val bestSellingCourses = homePageData2!!.allCoursesWithTeacherBestSelling

            if (bestSellingCourses != null) {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(), content = {

                        items(bestSellingCourses) { course ->

                            Spacer(modifier = Modifier.width(5.dp))
                            Card(
                                elevation = 2.dp, modifier = Modifier
                                    .width(260.dp)
                                    .padding(5.dp)
                            ) {

                                Column(
                                ) {

                                    GlideImage(
                                        model = LoadImageFormURLFixutils(
                                            course.thumbnail
                                        ),
                                        contentScale = ContentScale.Crop,
                                        contentDescription = "",
                                        modifier = Modifier
                                            .height(150.dp)
                                            .width(260.dp)
                                    )

                                    Column(modifier = Modifier.padding(15.dp)) {
                                        Text(
                                            text = course.nameTitle,
                                            color = Color.DarkGray,
                                            fontSize = 16.sp
                                        )
                                        Spacer(modifier = Modifier.height(5.dp))

                                        Text(
                                            text = "برنامه نویسی اندروید",
                                            color = Color.Gray,
                                            fontSize = 14.sp
                                        )
                                        Row(
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.fillMaxWidth()
                                        ) {

                                            Text(
                                                text = "مدرس : " + course.user.name,
                                                color = Color.Gray,
                                                fontSize = 13.sp
                                            )

                                            val priceCourse = course.price.toString() + " تومان "
                                            Text(text = priceCourse, color = Color(0xFF2E7D32),
                                                fontSize = 14.sp)
                                        }
                                    }
                                }
                            }

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


