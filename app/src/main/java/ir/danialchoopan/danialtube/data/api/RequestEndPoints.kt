package ir.danialchoopan.danialtube.data.api

object RequestEndPoints {
    val rootDomain = "http://192.168.43.166:8000"
    val rootDomainApi = "$rootDomain/api"
    val storageLoad = "$rootDomain/storage/"

    //transaction url
    val transactionUrl = "$rootDomainApi/transaction_course/" // arg course_id user_id

    //connection check
    val internetCheck = "$rootDomainApi/internet"

    //user auth requests
    val userRegister = "$rootDomainApi/auth/register"
    val userLogin = "$rootDomainApi/auth/login"
    val userLogout = "$rootDomainApi/auth/logout"

    //user phone sms valid
    val userRequestPhoneValidCode = "$rootDomainApi/user/phone/send/valid" //token
    val userSendPhoneValidCode = "$rootDomainApi/user/phone/code" //token
    val userCheckValidPhone = "$rootDomainApi/user/phone/check/valid" //token

    //use reset password
    val userRestPasswordCheckPhone = "$rootDomainApi/user/rest/password"
    val userRestPasswordRequestSms = "$rootDomainApi/user/rest/password/request/sms"
    val userRestPasswordSendSms = "$rootDomainApi/user/rest/password/send/sms"
    val userRestPasswordChangePassword = "$rootDomainApi/user/rest/change/password" //token

    //edit user
    val userEditPasswordEdit = "$rootDomainApi/user/edit/password/email" //token


    //user course
    val userCourseTransaction = "$rootDomainApi/user/profile/course/transaction" //token
    val userCourseFavorites = "$rootDomainApi/user/profile/course/favorites" //token

    //home page requests
    val homePage = "$rootDomainApi/homeWithNoAuth"
    val homePageMoreBestSelling = "$rootDomainApi/homeWithNoAuth/showMoreBestselling"
    val homePageMoreMostPopulars = "$rootDomainApi/homeWithNoAuth/showMoreMostPopulars"
    //show more course
    val showMoreCourse = "$rootDomainApi/course/more"

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

    //take a course
    val takeACourse =
        "$rootDomainApi/user/take/course" //arg id course POST
    val checkCourseToken = "$rootDomainApi/user/check/take/course"; //arg course_id POST

    //course favorite add remove check for show course
    val checkCourseFavorite = "$rootDomainApi/user/check/course/favorite"; //arg course_id POST
    val addCourseFavorite = "$rootDomainApi/user/add/course/favorite" // arg course_id POST
    val removeCourseFavorite = "$rootDomainApi/user/remove/course/favorite"// arg course_id POST

    //course comment
    val all4Comment="$rootDomainApi/course/comments/4" //arg course_id POST
    val allComments="$rootDomainApi/course/comments" //arg course_id POST
    val addComment="$rootDomainApi/course/add/comments" //arg course_id POST
    val removeComment="$rootDomainApi/course/delete/comments" //arg comment id  POST

}