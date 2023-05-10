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
import ir.danialchoopan.danialtube.data.api.model.courseShowCategory.ShowCategoriesCourse
import ir.danialchoopan.danialtube.data.api.model.courseShowCategory.SubCourseCategoriesCourse
import ir.danialchoopan.danialtube.data.api.model.showCategory.CourseCategory
import ir.danialchoopan.danialtube.utils.LoadImageFormURLFixutils

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterialApi::class)
@Composable

fun cardCategory(
    category_name:String,
    category_icon:String,
    onClick:()->Unit){
    Card(
        elevation = 2.dp, modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
        ,
        onClick = {
            onClick()
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            GlideImage(
                model = LoadImageFormURLFixutils(
                    category_icon
                ), contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(160.dp)
            )

            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = category_name,
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}

