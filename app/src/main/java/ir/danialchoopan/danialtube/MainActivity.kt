package ir.danialchoopan.danialtube

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.danialchoopan.danialtube.ui.theme.DanialTubeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DanialTubeTheme {
                //app route
                HomePageScreen()
                //  splash screen -> homepage
            }
        }
    }
}

@Composable
fun HomePageScreen() {
    val m_context = LocalContext.current
    val scaffoldState =
        rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
    val coroutin1=
        rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        coroutin1.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "")
                    }
                },
                title = { Text("DanialTube") })
        },
        drawerContent = { Text(text = "drawerContent") },
        content = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "",
                    modifier = Modifier.height(200.dp).fillMaxWidth().background(Color.Cyan)
                )
            }
        },
        bottomBar = {
            BottomNavigation() {
                BottomNavigationItem(
                    selected = true,
                    onClick = {
                        Toast.makeText(
                            m_context,
                            "home", Toast.LENGTH_SHORT
                        ).show()
                    },
                    icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "") },
                    label = { Text(text = "home") },
                )

                BottomNavigationItem(
                    selected = false,
                    onClick = {
                        Toast.makeText(
                            m_context,
                            "search", Toast.LENGTH_SHORT
                        ).show()
                    },
                    icon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
                    label = { Text(text = "search") },
                )
                BottomNavigationItem(
                    selected = false,
                    onClick = {
                        Toast.makeText(
                            m_context,
                            "profile", Toast.LENGTH_SHORT
                        ).show()
                    },
                    icon = { Icon(imageVector = Icons.Default.Person, contentDescription = "") },
                    label = { Text(text = "profile") },
                )

            }
        }
    )
}
