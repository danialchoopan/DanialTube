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
import ir.danialchoopan.danialtube.data.api.model.courseShow.CourseWithVideosUser
import ir.danialchoopan.danialtube.screen.HomePageScreen
import ir.danialchoopan.danialtube.screen.about.AboutUsScreen
import ir.danialchoopan.danialtube.screen.category.ShowAllCategoryScreen
import ir.danialchoopan.danialtube.screen.category.ShowAllCourseSubCategoryScreen
import ir.danialchoopan.danialtube.screen.category.ShowAllSubCategoryScreen
import ir.danialchoopan.danialtube.screen.category.homePageCategory.homePageCategoryScreen
import ir.danialchoopan.danialtube.screen.course.`check-out`.CourseCheckOutScreen
import ir.danialchoopan.danialtube.screen.course.comments.CourseCommentScreen
import ir.danialchoopan.danialtube.screen.course.myCoursesScreen
import ir.danialchoopan.danialtube.screen.course.myFavouriteCoursesScreen
import ir.danialchoopan.danialtube.screen.course.showCourseScreen
import ir.danialchoopan.danialtube.screen.course.videos.ShowVideosScreen
import ir.danialchoopan.danialtube.screen.homePage.courseMore.bestSellingCourseScreen
import ir.danialchoopan.danialtube.screen.homePage.courseMore.morePoplarCourseScreen
import ir.danialchoopan.danialtube.screen.privacy.PrivacyUsersScreen
import ir.danialchoopan.danialtube.screen.splash.splashScreen
import ir.danialchoopan.danialtube.screen.user.EditUserDataScreen
import ir.danialchoopan.danialtube.screen.user.ValidatePhoneNumberScreen
import ir.danialchoopan.danialtube.screen.user.forgotPassword.ForgotPasswordChangePasswordScreen
import ir.danialchoopan.danialtube.screen.user.forgotPassword.ForgotPasswordPhoneCheckScreen
import ir.danialchoopan.danialtube.screen.user.forgotPassword.ForgotPasswordPhoneVerifyScreen
import ir.danialchoopan.danialtube.ui.theme.DanialTubeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            DanialTubeTheme {
                //app route
                RightToLeftLayout {
                    NavHost(
                        navController = navController, startDestination = "splashScreen"
                    ) {

                        composable("splashScreen") {
                            splashScreen(navController)
                        }

                        composable("home") {
                            HomePageScreen(navController)
                        }

                        //course's screen
                        composable("course/{courseId}") { navBackStackEntry ->
                            val courseId = navBackStackEntry.arguments!!.getString("courseId")
                            showCourseScreen(navController, course_id = courseId.toString())
                        }
                        //video
                        composable("course/{course_id}/video/{video_id}") { navBackStackEntry ->
                            val course_id = navBackStackEntry.arguments!!.getString("course_id").toString()
                            val video_id = navBackStackEntry.arguments!!.getString("video_id").toString()
                            ShowVideosScreen(navController,course_id,video_id)
                        }


                        //comments
                        composable("course/{course_id}/comments") { navBackStackEntry ->
                            val course_id = navBackStackEntry.arguments!!.getString("course_id").toString()
                            CourseCommentScreen(navController,course_id)
                        }

                        composable("course/check-out/{course_id}"){navBackStackEntry ->
                            val course_id = navBackStackEntry.arguments!!.getString("course_id").toString()
                            CourseCheckOutScreen(navController,course_id)
                        }
                        composable("myCourses") {
                            myCoursesScreen(navController)
                        }

                        composable("myFavouriteCourses") {
                            myFavouriteCoursesScreen(navController)
                        }

                        //more course's screen
                        composable("morePoplarCourse") {
                            morePoplarCourseScreen(navController)
                        }

                        composable("bestSellingCourse") {
                            bestSellingCourseScreen(navController)
                        }

                        //end course screen

                        //category's screen
                        composable("category") {
                            ShowAllCategoryScreen(navController)
                        }

                        composable("subCategory/{category_id}") { navBackStackEntry ->
                            val courseId = navBackStackEntry.arguments!!.getString("category_id")
                            ShowAllSubCategoryScreen(
                                navController,
                                category_id = courseId.toString()
                            )
                        }

                        composable("homePageCategory/{category_id}") { navBackStackEntry ->
                            val courseId = navBackStackEntry.arguments!!.getString("category_id")
                            homePageCategoryScreen(navController, category_id = courseId.toString())
                        }


                        composable("subCategoryCourse/{sub_category_id}") { navBackStackEntry ->
                            val courseId =
                                navBackStackEntry.arguments!!.getString("sub_category_id")
                            ShowAllCourseSubCategoryScreen(
                                navController,
                                sub_category_id = courseId.toString()
                            )
                        }
                        //end category screen


                        //drawer menu
                        composable("aboutUs") {
                            AboutUsScreen(navController)
                        }

                        composable("userPrivacy") {
                            PrivacyUsersScreen(navController)
                        }

                        //edit user screen
                        composable("userEditPasswordEmail") {
                            EditUserDataScreen(navController)
                        }

                        //send code screen
                        composable("ValidatePhoneNumberScreen") {
                            ValidatePhoneNumberScreen(navController)
                        }

                        //forgot password

                        composable("forgotPasswordPhoneCheck") {
                            ForgotPasswordPhoneCheckScreen(navController)
                        }

                        composable("forgotPasswordPhoneVerify/{phone_number}") { navBackStackEntry ->
                            val phone_number =
                                navBackStackEntry.arguments!!.getString("phone_number").toString()
                            ForgotPasswordPhoneVerifyScreen(navController, phone_number)
                        }

                        composable("forgotPasswordChangePassword/phone/{phone_number}/token/{token}") { navBackStackEntry ->
                            val phone_number =
                                navBackStackEntry.arguments!!.getString("phone_number").toString()
                            val token =
                                navBackStackEntry.arguments!!.getString("token").toString()
                            ForgotPasswordChangePasswordScreen(
                                navController = navController,
                                phone_number = phone_number,
                                userToken = token
                            )
                        }

                        //end forgot password

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
