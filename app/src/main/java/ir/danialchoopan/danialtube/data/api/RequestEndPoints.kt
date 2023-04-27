package ir.danialchoopan.danialtube.data.api

object RequestEndPoints {
    val rootDomain = "http://192.168.111.10:8000/api"

    //user requests
    val userRegister = "$rootDomain/auth/register"
    val userLogin = "$rootDomain/auth/login"
    val userCheckPhoneValidation = "$rootDomain/user/phone/valid" //token
    val userSendVerifyCode = "$rootDomain/user/phone/code" //token

   //home page requests
}