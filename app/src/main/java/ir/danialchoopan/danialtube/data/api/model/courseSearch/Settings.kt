package ir.danialchoopan.danialtube.data.api.model.courseSearch


import com.google.gson.annotations.SerializedName

data class Settings(
    @SerializedName("locale")
    val locale: String
)