package ir.danialchoopan.danialtube.data.api.model.homepage


import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("course_id")
    val courseId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("length")
    val length: String,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("video")
    val video: String
)