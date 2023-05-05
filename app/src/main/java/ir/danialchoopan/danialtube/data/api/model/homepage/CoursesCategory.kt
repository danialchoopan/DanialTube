package ir.danialchoopan.danialtube.data.api.model.homepage


import com.google.gson.annotations.SerializedName

data class CoursesCategory(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("updated_at")
    val updatedAt: String
)