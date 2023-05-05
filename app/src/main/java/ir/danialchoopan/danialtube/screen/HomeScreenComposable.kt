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
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import ir.danialchoopan.danialtube.R
import ir.danialchoopan.danialtube.data.api.RequestEndPoints
import ir.danialchoopan.danialtube.data.api.model.homepage.HomePageRequestDataModel
import ir.danialchoopan.danialtube.data.api.requests.HomePageRequest
import ir.danialchoopan.danialtube.ui.componets.DialogBoxLoading
import ir.danialchoopan.danialtube.viewmodels.HomeScreenViewModel
import kotlinx.coroutines.delay


@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomePageScreenScaffoldContent(
    m_context: Context, homeScreenViewModel: HomeScreenViewModel
) {

    var homePageDataScreen by remember {
        mutableStateOf(HomePageRequestDataModel(null, null, null, null, emptyList()))
    }

    //other variables
    var onGoingProgress by remember {
        mutableStateOf(true)
    }

    if (onGoingProgress) {
        DialogBoxLoading()
    }


    //load data new way
//    var myLoadedData by remember {
//        mutableStateOf(HomePageRequestDataModel())
//    }
//
//    val homePagerequest13=HomePageRequest(m_context)
//    LaunchedEffect(Unit){
//        myLoadedData=homePagerequest13.homePage1()
//    }

//    if(myLoadedData!=null){
//        myLoadedData.homePageSlider?.get(0)?.let { Text(text = it.name) }
//        myLoadedData.homePageSlider?.get(0)?.let {         Toast.makeText(m_context,it.name,Toast.LENGTH_SHORT).show() }
//
//    }else{
//        Toast.makeText(m_context,"null data",Toast.LENGTH_SHORT).show()
//    }

    //end load data new way
    var homePageData2 by remember { mutableStateOf<HomePageRequestDataModel?>(null) }

    val homePageRequestVolley = HomePageRequest(LocalContext.current)
    LaunchedEffect(Unit) {
        homePageRequestVolley.homePage { success, homePageData ->
            homePageData2 = homePageData
        }
    }
    if (homePageData2 != null) {
        Text(text = homePageData2!!.homePageSlider?.get(1)!!.name)
        onGoingProgress = false
        val rememberScrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState)
        ) {


            val imageForSlider1 = homePageData2!!.homePageSlider

//            AsyncImage(
//                model = ImageRequest.Builder(LocalContext.current)
//                    .data(imgLoadUrlTest)
//                    .crossfade(true)
//                    .build(),
//                contentDescription = "",
//                contentScale = ContentScale.Crop,
//                modifier = Modifier.fillMaxWidth(),
//                onError = {
//                    Log.d("onError image loader coil ","on error")
//
//                    Log.d("onError image loader coil ",it.toString())
//                },
//                onLoading = {
//                    Log.d("onError image loader coil ","on loading")
//                }, onSuccess = {
//                    Log.d("onError image loader coil ","on success")
//                }
//            )


            Card{
                AutoSlidingCarousel(
                    itemsCount = imageForSlider1!!.size,
                    itemContent = { index ->
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(RequestEndPoints.storageLoad+imageForSlider1!![index].photo.replace("\\","/"))
                                .crossfade(true)
                                .build(),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxWidth().height(200.dp)
                        )
                    }
                )
            }

            Text(text = "درسته بندی ها", modifier = Modifier.padding(10.dp))
            //category

            LazyRow(
                modifier = Modifier.fillMaxWidth(), content = {
                    items(items = homePageData2?.coursesCategory!!) { categoryItem ->
                        Spacer(modifier = Modifier.width(5.dp))
                        Card(elevation = 2.dp, modifier = Modifier.width(110.dp)) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                AsyncImage(
                                    model = RequestEndPoints.storageLoad + categoryItem.icon.replace(
                                        "\\",
                                        "/"
                                    ), contentDescription = "",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )

                                Column(modifier = Modifier.padding(10.dp)) {
                                    Text(text = categoryItem.name, fontSize = 12.sp)
                                }
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                })

            Text(text = "محبوب ترین دوره ها", modifier = Modifier.padding(10.dp))


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(
                        rememberScrollState()
                    )
            ) {
                for (i in 1..10) {
                    Card(
                        elevation = 2.dp, modifier = Modifier
                            .width(250.dp)
                            .padding(5.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.ic_launcher_foreground),
                                contentDescription = "",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Black)
                            )
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(text = "آموزش برنامه نویسی اندروید")
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(text = "برنامه نویسی ")
                                Row() {
                                    Text(text = "20 ساعت")
                                    Text(text = "24.0 IRT")
                                }
                            }
                        }
                    }
                }
            }

            Text(text = "دوره منتخب", modifier = Modifier.padding(10.dp))
            Card(
                elevation = 2.dp, modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_launcher_foreground),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Cyan)
                    )
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(text = "آموزش برنامه نویسی اندروید")
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "برنامه نویسی ")
                        Row() {
                            Text(text = "20 ساعت")
                            Text(text = "24.0 IRT")
                        }
                    }
                }
            }
            Text(text = "جدید ترین", modifier = Modifier.padding(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(
                        rememberScrollState()
                    )
            ) {
                for (i in 1..10) {
                    Card(
                        elevation = 2.dp, modifier = Modifier
                            .width(250.dp)
                            .padding(5.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.ic_launcher_foreground),
                                contentDescription = "",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Green)
                            )
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(text = "آموزش برنامه نویسی اندروید")
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(text = "برنامه نویسی ")
                                Row() {
                                    Text(text = "20 ساعت")
                                    Text(text = "24.0 IRT")
                                }
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(80.dp))
        }
    } else {
        onGoingProgress = true
    }

}


//slider components

@Composable
fun IndicatorDot(
    modifier: Modifier = Modifier,
    size: Dp,
    color: Color
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color)
    )
}

@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color = Color.White,
    unSelectedColor: Color = Color.Gray,
    dotSize: Dp
) {
    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        items(totalDots) { index ->
            IndicatorDot(
                color = if (index == selectedIndex) selectedColor else unSelectedColor,
                size = dotSize
            )

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AutoSlidingCarousel(
    modifier: Modifier = Modifier,
    autoSlideDuration: Long = 3000,
    pagerState: PagerState = remember { PagerState() },
    itemsCount: Int,
    itemContent: @Composable (index: Int) -> Unit,
) {
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    LaunchedEffect(pagerState.currentPage) {
        delay(autoSlideDuration)
        pagerState.animateScrollToPage((pagerState.currentPage + 1) % itemsCount)
    }

    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        HorizontalPager(count = itemsCount, state = pagerState) { page ->
            itemContent(page)
        }

        // you can remove the surface in case you don't want
        // the transparant bacground
        Surface(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.BottomCenter),
            shape = CircleShape,
            color = Color.Black.copy(alpha = 0.5f)
        ) {
            DotsIndicator(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                totalDots = itemsCount,
                selectedIndex = if (isDragged) pagerState.currentPage else pagerState.targetPage,
                dotSize = 8.dp
            )
        }
    }
}

