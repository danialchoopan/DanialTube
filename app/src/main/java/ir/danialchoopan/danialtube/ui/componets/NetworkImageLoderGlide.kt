package ir.danialchoopan.danialtube.ui.componets

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumptech.glide.integration.compose.GlideImage

//@Composable
//fun GlideImgLoader(imgURL:String,modifier:Modifier=Modifier){
//    GlideImage(model = , contentDescription = ,)
//    GlideImage(
//        model = imgURL,
//        modifier = modifier,
//        // shows a progress indicator when loading an image.
//        loading = {
//            ConstraintLayout(
//                modifier = Modifier.fillMaxSize()
//            ) {
//                val indicator = createRef()
//                CircularProgressIndicator(
//                    modifier = Modifier.constrainAs(indicator) {
//                        top.linkTo(parent.top)
//                        bottom.linkTo(parent.bottom)
//                        start.linkTo(parent.start)
//                        end.linkTo(parent.end)
//                    }
//                )
//            }
//        },
//        // shows an error text message when request failed.
//        failure = {
//            Text(text = "image request failed.")
//        })
//}