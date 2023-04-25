package ir.danialchoopan.danialtube.ui.componets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import ir.danialchoopan.danialtube.R
import ir.danialchoopan.danialtube.ui.theme.myCustomFontVazir
import ir.danialchoopan.danialtube.ui.theme.typography_font_vazir

@Composable
fun DialogBoxLoading(
    cornerRadius: Dp = 16.dp,
    paddingStart: Dp = 56.dp,
    paddingEnd: Dp = 56.dp,
    paddingTop: Dp = 32.dp,
    paddingBottom: Dp = 32.dp,
    progressIndicatorColor: Color = Color(0xFF35898f),
    progressIndicatorSize: Dp = 80.dp
) {

    Dialog(
        onDismissRequest = {
        }
    ) {
        Surface(
            elevation = 4.dp,
            shape = RoundedCornerShape(cornerRadius)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = paddingStart, end = paddingEnd, top = paddingTop),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                CircularProgressIndicator(
                    modifier = Modifier.size(progressIndicatorSize),
                    color = progressIndicatorColor,
                    strokeWidth = 5.dp
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    modifier = Modifier
                        .padding(bottom = paddingBottom),
                    text = "در حال بارگذاری...",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontFamily = myCustomFontVazir,
                    )
                )
            }
        }
    }
}
