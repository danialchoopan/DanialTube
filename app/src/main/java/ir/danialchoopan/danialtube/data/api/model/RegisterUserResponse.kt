package ir.danialchoopan.danialtube.data.api.model


import com.google.gson.annotations.SerializedName

data class RegisterUserResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("token")
    val token: String,
    @SerializedName("user")
    val user: User
)