package ir.danialchoopan.danialtube.data.api.model.courseShow


import com.google.gson.annotations.SerializedName

data class ShowCourse(
    @SerializedName("courseWithVideosUser")
    val courseWithVideosUser: CourseWithVideosUser,
    @SerializedName("success")
    val success: Boolean
)