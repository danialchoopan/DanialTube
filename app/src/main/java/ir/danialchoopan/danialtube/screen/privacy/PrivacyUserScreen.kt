package ir.danialchoopan.danialtube.screen.privacy

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun PrivacyUsersScreen(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack, contentDescription = "",
                            modifier = Modifier.scale(-1f, 1f)
                        )
                    }
                },
                title = { Text("حریم خصوصی کاربران") })
        },
        content = {

            Text(
                text="توسعه\u200Cدهندگان باید از حریم خصوصی و حقوق قانونی کاربران محافظت کند. برنامه\u200Cهایی که اقدام به ثبت اطلاعات کاربران مانند شماره تلفن یا مشخصات فردی و مواردی مشابه می\u200Cکنند، باید متن حریم خصوصی را در برنامه خود قرار دهند. \n" +
                        "\n" +
                        "محل قرار گرفتن حریم خصوصی \n" +
                        "متن حریم خصوصی باید درون برنامه و در دسترس کاربران باشد. در برنامه\u200Cهایی که امکان استفاده از آن\u200Cها بدون ثبت\u200Cنام امکان\u200Cپذیر است، باید متن حریم خصوصی را در مکانی که به راحتی در دسترس کاربران باشد، قرار دهید. در مواردی که امکان ورود به برنامه\u200Cها بدون ثبت\u200Cنام ممکن نیست، در اولین مرحله\u200Cی ثبت نام، کاربران- باید از نحوه\u200Cی جمع\u200Cآوری اطلاعات، چگونگی حفظ این اطلاعات و علت دریافت این اطلاعات، به صورت شفاف آگاه شوند. این متن باید کاربران را به طور کامل در جریان عملیاتی که برنامه روی این اطلاعات انجام می\u200Cدهد قرار دهد. در نظر داشته باشید که برنامه\u200Cها نباید از اطلاعات کاربران استفاده\u200Cهای مشکوک، نامشخص و ناگفته کنند. \n" +
                        "\n" +
                        "نکته: توجه داشته باشید که اطلاعات دریافت شده از کاربر تنها به همان منظور دریافت\u200C شوند که به او توضیح داده\u200Cاید.\n" +
                        "اطلاعات شخصی و حساس\n" +
                        "داده\u200Cهای شخصی و حساس کاربر شامل اطلاعات شناسایی (کد\u200Cملی، تاریخ تولد و...)، اطلاعات مالی و پرداخت، اطلاعات احراز هویت، مخاطبین، ایمیل، موقعیت مکانی دستگاه، پیامک و داده\u200Cهای مربوط به تماس، میکروفون، دوربین و مواردی مشابه است. \n" +
                        "\n" +
                        "اگر برنامه شما از اطلاعات شخصی و حساس کاربر استفاده می کند، باید موارد زیر را در نظر بگیرید:\n" +
                        "\n" +
                        "شما باید در نحوهٔ مدیریت داده\u200Cهای کاربر شفاف باشید، برای مثال اطلاعات جمع آوری شده از یک کاربر، از جمله اطلاعات دستگاه در این چارچوب قرار می\u200Cگیرد. این به معنای اطلاع دادن به کاربر در مورد دسترسی، جمع آوری، استفاده و به اشتراک گذاری داده\u200Cهای برنامه شما و محدود کردن استفاده از داده\u200Cها به اهداف اشاره شده است.\n" +
                        "نکته: برای مثال اگر از شماره همراه کاربر تنها برای ثبت\u200Cنام در برنامه اسفاده می\u200C\u200Cکنید، لازم است استفاده از آن را فقط به همین مورد محدود کنید و آن را در اختیار دیگران قرار ندهید.",
                modifier = Modifier.padding(15.dp),
                color = Color.Gray, fontSize = 14.sp
            )
        }
    )
}