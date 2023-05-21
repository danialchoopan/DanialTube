package ir.danialchoopan.danialtube.data.api.model.courseComment


import com.google.gson.annotations.SerializedName

data class CourseCommentsItem(
    @SerializedName("comment")
    val comment: String,
    @SerializedName("course_id")
    val courseId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user")
    val user: User,
    @SerializedName("user_id")
    val userId: Int
)