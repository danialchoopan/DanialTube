package ir.danialchoopan.danialtube.data.api.model.myFavouriteCourses


import com.google.gson.annotations.SerializedName

data class MyFavouriteCoursesModelItem(
    @SerializedName("course")
    val course: Course,
    @SerializedName("course_id")
    val courseId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("token")
    val token: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user")
    val user: UserX,
    @SerializedName("user_id")
    val userId: Int
)