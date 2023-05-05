package ir.danialchoopan.danialtube.data.api.model.homepage


import com.google.gson.annotations.SerializedName

data class Settings(
    @SerializedName("locale")
    val locale: String
)