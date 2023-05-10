package ir.danialchoopan.danialtube.data.api.model.myCourses


import com.google.gson.annotations.SerializedName

data class Settings(
    @SerializedName("locale")
    val locale: String
)