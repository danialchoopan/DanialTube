package ir.danialchoopan.danialtube.data.api

object RequestEndPoints {
    val rootDomain = "http://192.168.111.10:8000"
    val rootDomainApi = "$rootDomain/api"
    val storageLoad="$rootDomain/storage/"

    //user auth requests
    val userRegister = "$rootDomainApi/auth/register"
    val userLogin = "$rootDomainApi/auth/login"
    val userLogout = "$rootDomainApi/auth/logout"

    //user requests
    val userCheckPhoneValidation = "$rootDomainApi/user/phone/valid" //token
    val userSendVerifyCode = "$rootDomainApi/user/phone/code" //token
    //user course
    val userCourseTransaction = "$rootDomainApi/user/profile/course/transaction" //token
    val userCourseFavorites = "$rootDomainApi/user/profile/course/favorites" //token

   //home page requests
   val homePage = "$rootDomainApi/homeWithNoAuth"

    
    //course
    val showCourseById = "$rootDomainApi/course/show"
    val searchCourse = "$rootDomainApi/course/search"
    //course category
    val courseCategory = "$rootDomainApi/course/categories"
    val subCourseCategory = "$rootDomainApi/course/sub/categories" //arg id category
    val subCourseCategoryCourses = "$rootDomainApi/course/sub/category/courses" //arg id sub category


    val courseFavorites = "$rootDomainApi/user/profile/course/favorites"//arg get {id} => course/show/{id}
    val myCourse = "$rootDomainApi/user/profile/course/transaction"//arg get {id} => course/show/{id}
}