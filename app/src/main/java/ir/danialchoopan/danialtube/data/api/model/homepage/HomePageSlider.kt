package ir.danialchoopan.danialtube.data.api.model.homepage


import com.google.gson.annotations.SerializedName

data class HomePageSlider(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("on_click")
    val onClick: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("updated_at")
    val updatedAt: String
)