package ir.danialchoopan.danialtube.data.api.model.homepage


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("email_verified_at")
    val emailVerifiedAt: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone_number")
    val phoneNumber: Any,
    @SerializedName("phone_verified_at")
    val phoneVerifiedAt: Any,
    @SerializedName("role_id")
    val roleId: Int,
    @SerializedName("settings")
    val settings: Settings,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user_token_login_qr")
    val userTokenLoginQr: Any
)