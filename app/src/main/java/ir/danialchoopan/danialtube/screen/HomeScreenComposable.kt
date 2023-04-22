package ir.danialchoopan.danialtube.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ir.danialchoopan.danialtube.R


@Composable
fun HomePageScreenScaffoldContent(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "",
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .background(Color.Cyan)
        )
        Text(text = "درسته بندی ها", modifier = Modifier.padding(10.dp))
        //category
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(
                    rememberScrollState()
                )
        ) {
            Spacer(modifier = Modifier.width(5.dp))
            for (i in 1..10) {
                Card(elevation = 2.dp, modifier = Modifier.width(110.dp)) {
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
                            Text(text = "برنامه نویسی ")
                        }
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
            }

        }
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
}