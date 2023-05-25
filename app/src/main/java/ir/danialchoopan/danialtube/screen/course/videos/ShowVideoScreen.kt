package ir.danialchoopan.danialtube.screen.course.videos

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import ir.danialchoopan.danialtube.data.api.model.courseComment.CourseComments
import ir.danialchoopan.danialtube.data.api.model.courseShow.ShowCourse
import ir.danialchoopan.danialtube.data.api.model.myCourses.MyCoursesModel
import ir.danialchoopan.danialtube.data.api.requests.course.CourseRequest
import ir.danialchoopan.danialtube.ui.componets.DialogBoxLoading
import ir.danialchoopan.danialtube.ui.componets.VidoesCardShowListComponent
import ir.danialchoopan.danialtube.utils.IsUserLogin
import ir.danialchoopan.danialtube.utils.LoadImageFormURLFixutils

@Composable
fun ShowVideosScreen(navController: NavController, course_id: String, video_id: String) {
    val m_context = LocalContext.current
    val userDataLoad = m_context.getSharedPreferences("user_data", Context.MODE_PRIVATE)

    val m_activity =m_context as Activity
    m_activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

    var onGoingProgress by remember {
        mutableStateOf(true)
    }

    if (onGoingProgress) {
        DialogBoxLoading()
    }
    var course_video_loaded by remember {
        mutableStateOf("")
    }

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
                title = { Text("ویدیو های آموزشی دوره  ") })
        },
        content = {

            var courseDataShow by remember { mutableStateOf<ShowCourse?>(null) }

            val courseRequestVolley = CourseRequest(m_context)
            LaunchedEffect(Unit) {
                courseRequestVolley.showCourse(course_id) {
                    courseDataShow = it
                    onGoingProgress = false
                }
            }





            if (courseDataShow != null) {
                course_video_loaded=courseDataShow!!.courseWithVideosUser.videos.find {
                    it.id == video_id.toInt()
                }!!.video

                val mVideoUrl =
                    LoadImageFormURLFixutils(course_video_loaded)

                val course_videos = courseDataShow!!.courseWithVideosUser.videos
                // Declaring ExoPlayer
                var mExoPlayer = remember(m_context) {
                    ExoPlayer.Builder(m_context).build().apply {
                        val dataSourceFactory = DefaultDataSourceFactory(
                            m_context,
                            Util.getUserAgent(m_context, m_context.packageName)
                        )
                        val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                            .createMediaSource(Uri.parse(mVideoUrl))
                        prepare(source)
                    }
                }
                AndroidView(
                    modifier =
                    Modifier
                        .fillMaxSize()
                        .background(Color.Black), factory = { context ->
                        PlayerView(context).apply {
                            player = mExoPlayer

                        }
                    })

//                LazyColumn(
//                    modifier = Modifier.fillMaxSize(), content = {
//                        item {
//                            AndroidView(
//                                modifier =
//                                Modifier
//                                    .fillMaxWidth()
//                                    .height(220.dp)
//                                    .background(Color.Black), factory = { context ->
//                                    PlayerView(context).apply {
//                                        player = mExoPlayer
//
//                                    }
//                                })
//                        }
//
//                        items(course_videos) {video->
//                            VidoesCardShowListComponent(
//                                thumbnail = video.thumbnail,
//                                nameTitle = video.title,
//                                onClick = {
//                                    course_video_loaded=video.video
//                                })
//                        }
//
//                    }
//
//                )


            }
        }
    )
}