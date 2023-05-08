package ir.danialchoopan.danialtube.data.api.model.courseShow


import com.google.gson.annotations.SerializedName

data class Settings(
    @SerializedName("locale")
    val locale: String
)