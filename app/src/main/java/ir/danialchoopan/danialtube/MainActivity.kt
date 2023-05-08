package ir.danialchoopan.danialtube

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.danialchoopan.danialtube.screen.HomePageScreen
import ir.danialchoopan.danialtube.screen.course.showCourseScreen
import ir.danialchoopan.danialtube.ui.theme.DanialTubeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController= rememberNavController()

            DanialTubeTheme {
                //app route
                RightToLeftLayout {
                    NavHost(navController = navController
                        ,startDestination ="home"){

                        composable("home"){
                            HomePageScreen(navController)
                        }

                        composable("course/{courseId}"){navBackStackEntry->
                            val courseId= navBackStackEntry.arguments!!.getString("courseId")
                            Log.d("courseId 12345 show navigate ", courseId.toString())
                            showCourseScreen(navController,course_id = courseId.toString())
                        }

                    }
                }
                //  splash screen -> homepage
            }
        }
    }
}


@Composable
fun RightToLeftLayout(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        content()
    }
}
