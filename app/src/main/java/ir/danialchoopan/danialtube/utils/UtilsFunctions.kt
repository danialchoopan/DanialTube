package ir.danialchoopan.danialtube.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import ir.danialchoopan.danialtube.data.api.RequestEndPoints
import java.text.DecimalFormat


//fix url
fun LoadImageFormURLFixutils(img:String):String{
    return RequestEndPoints.storageLoad +img.replace(
        "\\",
        "/"
    )
}

//reload
fun reloadHomePage(navController: NavController){
    navController.popBackStack()
    navController.navigate("home")
}

//format numbers
fun formatPrice(number: String): String {
    val decimalFormat = DecimalFormat("###,###")
    return decimalFormat.format(number.toInt())
}

//bottom border
fun Modifier.bottomBorder(strokeWidth: Dp, color: Color) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }

        Modifier.drawBehind {
            val width = size.width
            val height = size.height - strokeWidthPx/2

            drawLine(
                color = color,
                start = Offset(x = 0f, y = height),
                end = Offset(x = width , y = height),
                strokeWidth = strokeWidthPx
            )
        }
    }
)