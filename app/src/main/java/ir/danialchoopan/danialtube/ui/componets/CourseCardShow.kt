package ir.danialchoopan.danialtube.ui.componets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ir.danialchoopan.danialtube.data.api.model.CourseCardShow
import ir.danialchoopan.danialtube.data.api.model.homepage.CoursesWithTeacherMostPopular
import ir.danialchoopan.danialtube.utils.LoadImageFormURLFixutils
import ir.danialchoopan.danialtube.utils.formatPrice

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterialApi::class)
@Composable
fun courseCardShowComponent(
    thumbnail: String,
    nameTitle: String,
    categoryName: String,
    teacherName: String,
    coursePrice: String,
    onClick: () -> Unit,
    modifierCard: Modifier = Modifier,
    modifierImg: Modifier = Modifier
) {
    Card(
        elevation = 2.dp, modifier = modifierCard
            .width(260.dp)
            .padding(5.dp),
        onClick = {
            onClick()
        }
    ) {

        Column(
        ) {

            GlideImage(
                model = LoadImageFormURLFixutils(
                    thumbnail
                ),
                contentScale = ContentScale.Crop,
                contentDescription = "",
                modifier = modifierImg
                    .height(150.dp)
                    .width(260.dp)
            )

            Column(modifier = Modifier.padding(15.dp)) {
                Text(
                    text = nameTitle,
                    color = Color.DarkGray,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = categoryName,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = "مدرس : " + teacherName,
                        color = Color.Gray,
                        fontSize = 13.sp
                    )
                    if(coursePrice=="0"){
                        Text(
                            text = "رایگان", color = Color(0xFF2E7D32),
                            fontSize = 14.sp
                        )
                    }else{
                        val priceCourse = formatPrice(coursePrice) + " تومان "
                        Text(
                            text = priceCourse, color = Color(0xFF2E7D32),
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun courseCardShowComment(
    comment: String,
    username: String,
) {
    Card(
        elevation = 2.dp, modifier = Modifier
            .width(260.dp)
            .height(120.dp)
            .padding(5.dp),
    ) {

        Column(
        ) {

            Column(modifier = Modifier.fillMaxWidth().height(150.dp).padding(15.dp), verticalArrangement = Arrangement.Center,  horizontalAlignment = Alignment.Start) {
                Text(
                    text = comment,
                    color = Color.DarkGray,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = username,
                        color = Color.Gray,
                        fontSize = 13.sp
                    )

                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterialApi::class)
@Composable
fun courseCardShowListComponent(
    thumbnail: String,
    nameTitle: String,
    categoryName: String,
    teacherName: String,
    coursePrice: String,
    onClick: () -> Unit,
    modifierCard: Modifier = Modifier,
//    modifierImg: Modifier = Modifier
) {
    Card(
        elevation = 2.dp, modifier = modifierCard
            .fillMaxWidth()
            .padding(5.dp),
        onClick = {
            onClick()
        }
    ) {

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            GlideImage(
                model = LoadImageFormURLFixutils(
                    thumbnail
                ),
                contentScale = ContentScale.Fit,
                contentDescription = "",
                modifier = Modifier
                    .width(150.dp)
                    .fillMaxHeight()
            )

            Column(modifier = Modifier.padding(15.dp)) {

                Text(
                    text = nameTitle,
                    color = Color.DarkGray,
                    fontSize = 16.sp
                )
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Text(
//                        text = nameTitle,
//                        color = Color.DarkGray,
//                        fontSize = 16.sp
//                    )
////
////                    Icon(
////                        imageVector = Icons.Default.FavoriteBorder,
////                        contentDescription = "",
////                        tint = Color.Gray,
//////                        modifier = Modifier.size(70.dp)
////                    )
//
//                }
                Spacer(modifier = Modifier.height(5.dp))


                    Text(
                        text = categoryName,
                        color = Color.Gray,
                        fontSize = 14.sp
                    )



                Text(
                    text = "مدرس : " + teacherName,
                    color = Color.Gray,
                    fontSize = 13.sp
                )


                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if(coursePrice=="0") {
                        Text(
                            text = "رایگان", color = Color(0xFF2E7D32),
                            fontSize = 14.sp
                        )
                    }else{
                        val priceCourse = formatPrice(coursePrice) + " تومان "
                        Text(
                            text = priceCourse, color = Color(0xFF2E7D32),
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterialApi::class)
@Composable
fun courseCardShowComponentGrid2(
    thumbnail: String,
    nameTitle: String,
    categoryName: String,
    teacherName: String,
    coursePrice: String,
    onClick: () -> Unit,
    modifierCard: Modifier = Modifier,
    modifierImg: Modifier = Modifier
) {
    Card(
        elevation = 2.dp, modifier = modifierCard
            .width(260.dp)
            .padding(5.dp),
        onClick = {
            onClick()
        }
    ) {

        Column(
        ) {

            GlideImage(
                model = LoadImageFormURLFixutils(
                    thumbnail
                ),
                contentScale = ContentScale.Crop,
                contentDescription = "",
                modifier = modifierImg
                    .height(150.dp)
                    .width(260.dp)
            )

            Column(modifier = Modifier.padding(15.dp)) {
                Text(
                    text = nameTitle,
                    color = Color.DarkGray,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = categoryName,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = "مدرس : " + teacherName,
                        color = Color.Gray,
                        fontSize = 13.sp
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    if(coursePrice=="0"){
                        Text(text = "رایگان", color = Color(0xFF2E7D32),
                            fontSize = 14.sp)
                    }else{
                        val priceCourse = formatPrice(coursePrice) + " تومان "
                        Text(
                            text = priceCourse, color = Color(0xFF2E7D32),
                            fontSize = 14.sp
                        )
                    }

                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterialApi::class)
@Composable
fun courseCardShowMoreComponent(
    show_text:String="نمایش دوره های بیشتر",
    onClick: () -> Unit,
    modifierCard: Modifier = Modifier,
) {
    Card(
        elevation = 2.dp, modifier = modifierCard
            .width(260.dp)
            .padding(5.dp),
        onClick = {
            onClick()
        }
    ) {
        Column(
            modifier = modifierCard
                .width(260.dp)
                .height(255.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier.size(65.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = show_text, fontSize = 16.sp, color = Color.Gray)

        }
    }
}


