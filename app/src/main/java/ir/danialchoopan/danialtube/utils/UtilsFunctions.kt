package ir.danialchoopan.danialtube.utils

import android.content.Context
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


//is Login
fun IsUserLogin(m_context: Context): Boolean {
    return m_context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
        .getString("has_login", "no_login") == "has_login"
}

//fix url
fun LoadImageFormURLFixutils(img: String): String {
    return RequestEndPoints.storageLoad + img.replace(
        "\\",
        "/"
    )
}

//reload
fun reloadHomePage(navController: NavController) {
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
            val height = size.height - strokeWidthPx / 2

            drawLine(
                color = color,
                start = Offset(x = 0f, y = height),
                end = Offset(x = width, y = height),
                strokeWidth = strokeWidthPx
            )
        }
    }
)

fun isValidEmail(email: String): Boolean {
    if (email.isEmpty()) {
        return false
    }

    val index = email.indexOf("@")
    if (index == -1) {
        return false
    }

    val localPart = email.substring(0, index)
    val domain = email.substring(index + 1)

    if (!localPart.matches(Regex("^[a-zA-Z0-9_.]+$"))) {
        return false
    }

    if (!domain.matches(Regex("^[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$"))) {
        return false
    }
    return true
}

fun isValidPhoneNumber(phoneNumber: String): Boolean {

    if (phoneNumber.isEmpty()) {
        return false
    }

    if (phoneNumber.length != 11) {
        return false
    }

    if (!phoneNumber.startsWith("09")) {
        return false
    }

    val regex = Regex("^09[0-9]{9}$")
    if (!regex.matches(phoneNumber)) {
        return false
    }

    return true
}
