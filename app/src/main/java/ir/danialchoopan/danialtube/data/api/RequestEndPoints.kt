package ir.danialchoopan.danialtube.data.api

object RequestEndPoints {
    val rootDomain = "http://192.168.111.10:8000"
    val rootDomainApi = "$rootDomain/api"
    val storageLoad="$rootDomain/storage/"

    //user requests
    val userRegister = "$rootDomainApi/auth/register"
    val userLogin = "$rootDomainApi/auth/login"
    val userCheckPhoneValidation = "$rootDomainApi/user/phone/valid" //token
    val userSendVerifyCode = "$rootDomainApi/user/phone/code" //token

   //home page requests
   val homePage = "$rootDomainApi/homeWithNoAuth"

    
    //course
    val showCourseById = "$rootDomainApi/course/show"//arg get {id} => course/show/{id}
}