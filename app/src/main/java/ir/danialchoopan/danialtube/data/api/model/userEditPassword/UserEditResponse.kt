package ir.danialchoopan.danialtube.data.api.model.userEditPassword


import com.google.gson.annotations.SerializedName

data class UserEditResponse(
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
    val phoneNumber: String,
    @SerializedName("phone_verified_at")
    val phoneVerifiedAt: Any,
    @SerializedName("role_id")
    val roleId: Int,
    @SerializedName("settings")
    val settings: List<Any>,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user_token_login_qr")
    val userTokenLoginQr: Any
)