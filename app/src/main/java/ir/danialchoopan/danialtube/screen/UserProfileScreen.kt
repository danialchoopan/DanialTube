package ir.danialchoopan.danialtube.screen

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.danialchoopan.danialtube.viewmodels.HomeScreenViewModel


@Composable
fun UserProfileScreen(
    m_context: Context,
    homeScreenViewModel: HomeScreenViewModel
) {
    val userData=homeScreenViewModel.getUserLoginData(m_context)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically) {
            Column() {
                Text(text = userData.name)
                Text(text = userData.email)   
            }
            OutlinedButton(onClick = {

            }) {
                Text(text = "خروج")
            }
        }

        Text(text = "دوره های شرکت کرده")
        Text(text = "علاقه مندی ها ")
        Text(text = "دوره های خریداری شده")
    }
}