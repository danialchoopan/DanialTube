package ir.danialchoopan.danialtube.ui.componets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
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


