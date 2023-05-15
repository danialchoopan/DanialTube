package ir.danialchoopan.danialtube.data.api

object RequestEndPoints {
    val rootDomain = "http://192.168.111.10:8000"
    val rootDomainApi = "$rootDomain/api"
    val storageLoad = "$rootDomain/storage/"

    //user auth requests
    val userRegister = "$rootDomainApi/auth/register"
    val userLogin = "$rootDomainApi/auth/login"
    val userLogout = "$rootDomainApi/auth/logout"

    //user phone sms valid
    val userRequestPhoneValidCode = "$rootDomainApi/user/phone/send/valid" //token
    val userSendPhoneValidCode = "$rootDomainApi/user/phone/code" //token
    val userCheckValidPhone = "$rootDomainApi/user/phone/check/valid" //token

    //edit user
    val userEditPasswordEdit = "$rootDomainApi/user/edit/password/email" //token


    //user course
    val userCourseTransaction = "$rootDomainApi/user/profile/course/transaction" //token
    val userCourseFavorites = "$rootDomainApi/user/profile/course/favorites" //token

    //home page requests
    val homePage = "$rootDomainApi/homeWithNoAuth"
    val homePageMoreBestSelling = "$rootDomainApi/homeWithNoAuth/showMoreBestselling"
    val homePageMoreMostPopulars = "$rootDomainApi/homeWithNoAuth/showMoreMostPopulars"


    //course
    val showCourseById = "$rootDomainApi/course/show"
    val searchCourse = "$rootDomainApi/course/search"

    //course category
    val courseCategory = "$rootDomainApi/course/categories"
    val subCourseCategory = "$rootDomainApi/course/sub/categories" //arg id category
    val subCourseCategoryCourses =
        "$rootDomainApi/course/sub/category/courses" //arg id sub category


    //course favorite
    val courseFavorites =
        "$rootDomainApi/user/profile/course/favorites"
    val myCourse =
        "$rootDomainApi/user/profile/course/transaction"

    val addCourseFavorite = "$rootDomainApi/user/add/course/favorite" // arg course_id GET
    val removeCourseFavorite = "$rootDomainApi/user/remove/course/favorite"// arg course_id GET


}