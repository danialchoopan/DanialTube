package ir.danialchoopan.danialtube.data.api.model.courseSearch


import com.google.gson.annotations.SerializedName

data class SearchCourseModel(
    @SerializedName("courseWithVideosUserSearch")
    var courseWithVideosUserSearch: List<CourseWithVideosUserSearch>,
    @SerializedName("success")
    val success: Boolean
)