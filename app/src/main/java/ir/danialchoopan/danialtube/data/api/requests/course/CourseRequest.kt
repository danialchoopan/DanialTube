package ir.danialchoopan.danialtube.data.api.requests.course

import android.content.Context
import android.util.Log
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import ir.danialchoopan.danialtube.data.api.RequestEndPoints
import ir.danialchoopan.danialtube.data.api.VolleySingleTon
import ir.danialchoopan.danialtube.data.api.model.courseComment.CourseComments
import ir.danialchoopan.danialtube.data.api.model.courseSearch.SearchCourseModel
import ir.danialchoopan.danialtube.data.api.model.courseShow.ShowCourse
import ir.danialchoopan.danialtube.data.api.model.moreBestSelling.MoreBestSellingCourseModel
import ir.danialchoopan.danialtube.data.api.model.moreCourse.MoreCourseShowCourse
import ir.danialchoopan.danialtube.data.api.model.moreMostPopular.MorePopularCourseModel
import ir.danialchoopan.danialtube.data.api.model.myCourses.MyCoursesModel
import ir.danialchoopan.danialtube.data.api.model.myFavouriteCourses.MyFavouriteCoursesModel
import org.json.JSONObject

class CourseRequest(val m_context: Context) {
    val userSharedPreferences = m_context.getSharedPreferences("user_data", Context.MODE_PRIVATE)

    fun showCourse(course_id: String, resultRequest: (showCourse: ShowCourse) -> Unit) {
        val str_request_show_course =
            object : StringRequest(
                Method.GET, RequestEndPoints.showCourseById + "/" + course_id,
                { strResponse ->
                    try {

                        Log.d("show course 5473", strResponse)
                        val showCourseRequest =
                            Gson().fromJson(strResponse, ShowCourse::class.java)
                        resultRequest(showCourseRequest)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                //error
                , {
                    it.printStackTrace()
                }) {

                override fun getHeaders(): MutableMap<String, String> {
                    val requestHeaders = HashMap<String, String>()
//                    requestHeaders["Authorization"] = "Bearer $token_access";
                    return requestHeaders

                }

                override fun getParams(): MutableMap<String, String> {
                    val m_params = HashMap<String, String>()

                    return m_params
                }

            }//end request register
        VolleySingleTon.getInstance(m_context).addToRequestQueue(str_request_show_course)
    }

    fun searchCourse(
        searchQuery: String,
        resultRequest: (searchCourseRequest: SearchCourseModel) -> Unit
    ) {
        val str_request_search_course =
            object : StringRequest(
                Method.GET, RequestEndPoints.searchCourse + "/" + searchQuery,
                { strResponse ->
                    Log.d("search course 5473", strResponse)

                    try {
                        val searchCourseRequest =
                            Gson().fromJson(strResponse, SearchCourseModel::class.java)
                        resultRequest(searchCourseRequest)

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                //error
                , {
                    it.printStackTrace()
                }) {

                override fun getHeaders(): MutableMap<String, String> {
                    val requestHeaders = HashMap<String, String>()
//                    requestHeaders["Authorization"] = "Bearer $token_access";
                    return requestHeaders

                }

                override fun getParams(): MutableMap<String, String> {
                    val m_params = HashMap<String, String>()

                    return m_params
                }

            }//end request register
        VolleySingleTon.getInstance(m_context).addToRequestQueue(str_request_search_course)
    }

    fun MyCourses(resultRequest: (myCoursesModel: MyCoursesModel) -> Unit) {
        val str_request =
            object : StringRequest(
                Method.POST, RequestEndPoints.myCourse,
                { strResponse ->
                    Log.d("str response my course ", strResponse)
                    try {
                        val myCoursesModel =
                            Gson().fromJson(strResponse, MyCoursesModel::class.java)
                        resultRequest(myCoursesModel)

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                //error
                , {
                    it.printStackTrace()
                }) {

                override fun getHeaders(): MutableMap<String, String> {
                    val requestHeaders = HashMap<String, String>()
                    val token_access = userSharedPreferences.getString("token", "")
                    requestHeaders["Authorization"] = "Bearer $token_access";
                    return requestHeaders

                }

                override fun getParams(): MutableMap<String, String> {
                    val m_params = HashMap<String, String>()

                    return m_params
                }
            }//end request register
        VolleySingleTon.getInstance(m_context).addToRequestQueue(str_request)
    }

    fun MyFavoritesCourses(resultRequest: (myFavouriteCoursesModel: MyFavouriteCoursesModel) -> Unit) {
        val str_request =
            object : StringRequest(
                Method.POST, RequestEndPoints.courseFavorites,
                { strResponse ->
                    try {
                        val myFavouriteCoursesModel =
                            Gson().fromJson(strResponse, MyFavouriteCoursesModel::class.java)
                        resultRequest(myFavouriteCoursesModel)

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                //error
                , {
                    it.printStackTrace()
                }) {

                override fun getHeaders(): MutableMap<String, String> {
                    val requestHeaders = HashMap<String, String>()
                    val token_access = userSharedPreferences.getString("token", "")
                    requestHeaders["Authorization"] = "Bearer $token_access";
                    return requestHeaders

                }

                override fun getParams(): MutableMap<String, String> {
                    val m_params = HashMap<String, String>()

                    return m_params
                }
            }//end request register
        VolleySingleTon.getInstance(m_context).addToRequestQueue(str_request)
    }

    fun ShowMoreBestsellingCourses(resultRequest: (ShowMoreBestsellingCourses: MoreBestSellingCourseModel) -> Unit) {
        val str_request =
            object : StringRequest(
                Method.GET, RequestEndPoints.homePageMoreBestSelling,
                { strResponse ->
                    try {
                        val moreBestSellingCourseModel =
                            Gson().fromJson(strResponse, MoreBestSellingCourseModel::class.java)
                        resultRequest(moreBestSellingCourseModel)

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                //error
                , {
                    it.printStackTrace()
                }) {

                override fun getHeaders(): MutableMap<String, String> {
                    val requestHeaders = HashMap<String, String>()

                    return requestHeaders

                }

                override fun getParams(): MutableMap<String, String> {
                    val m_params = HashMap<String, String>()

                    return m_params
                }
            }//end request register
        VolleySingleTon.getInstance(m_context).addToRequestQueue(str_request)
    }

    fun ShowMorePopularCourses(resultRequest: (morePopularCourseModel: MorePopularCourseModel) -> Unit) {
        val str_request =
            object : StringRequest(
                Method.GET, RequestEndPoints.homePageMoreMostPopulars,
                { strResponse ->
                    try {
                        val morePopularCourseModel =
                            Gson().fromJson(strResponse, MorePopularCourseModel::class.java)
                        resultRequest(morePopularCourseModel)

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                //error
                , {
                    it.printStackTrace()
                }) {

                override fun getHeaders(): MutableMap<String, String> {
                    val requestHeaders = HashMap<String, String>()

                    return requestHeaders

                }

                override fun getParams(): MutableMap<String, String> {
                    val m_params = HashMap<String, String>()

                    return m_params
                }
            }//end request register
        VolleySingleTon.getInstance(m_context).addToRequestQueue(str_request)
    }

    fun ShowMoreCourses(resultRequest: (moreCourse: MoreCourseShowCourse) -> Unit) {
        val str_request =
            object : StringRequest(
                Method.GET, RequestEndPoints.showMoreCourse,
                { strResponse ->
                    try {
                        val moreCourse =
                            Gson().fromJson(strResponse, MoreCourseShowCourse::class.java)
                        resultRequest(moreCourse)

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                //error
                , {
                    it.printStackTrace()
                }) {

                override fun getHeaders(): MutableMap<String, String> {
                    val requestHeaders = HashMap<String, String>()

                    return requestHeaders

                }

                override fun getParams(): MutableMap<String, String> {
                    val m_params = HashMap<String, String>()

                    return m_params
                }
            }//end request register
        VolleySingleTon.getInstance(m_context).addToRequestQueue(str_request)
    }


    //check favorite

    fun CheckFavoriteCourses(course_id: String, result: (favorite: Boolean) -> Unit) {
        val str_request =
            object : StringRequest(
                Method.POST, RequestEndPoints.checkCourseFavorite + "/$course_id",
                { strResponse ->
                    val jsonResponseObject = JSONObject(strResponse)
                    result(jsonResponseObject.getBoolean("status"))
                }
                //error
                , {
                    it.printStackTrace()
                }) {

                override fun getHeaders(): MutableMap<String, String> {
                    val requestHeaders = HashMap<String, String>()
                    val token_access = userSharedPreferences.getString("token", "")
                    requestHeaders["Authorization"] = "Bearer $token_access";
                    return requestHeaders

                }

                override fun getParams(): MutableMap<String, String> {
                    val m_params = HashMap<String, String>()
                    return m_params
                }
            }//end request register
        VolleySingleTon.getInstance(m_context).addToRequestQueue(str_request)
    }

    //add favorite
    fun AddToFavoriteCourses(course_id: String, success: () -> Unit) {
        val str_request =
            object : StringRequest(
                Method.POST, RequestEndPoints.addCourseFavorite + "/$course_id",
                { strResponse ->
                    success()
                }
                //error
                , {
                    it.printStackTrace()
                }) {

                override fun getHeaders(): MutableMap<String, String> {
                    val requestHeaders = HashMap<String, String>()
                    val token_access = userSharedPreferences.getString("token", "")
                    requestHeaders["Authorization"] = "Bearer $token_access";
                    return requestHeaders

                }

                override fun getParams(): MutableMap<String, String> {
                    val m_params = HashMap<String, String>()
                    return m_params
                }
            }//end request register
        VolleySingleTon.getInstance(m_context).addToRequestQueue(str_request)
    }

    //remove favorite
    fun RemoveFromFavoriteCourses(course_id: String, success: () -> Unit) {
        val str_request =
            object : StringRequest(
                Method.POST, RequestEndPoints.removeCourseFavorite + "/$course_id",
                { strResponse ->
                    success()
                }
                //error
                , {
                    it.printStackTrace()
                }) {

                override fun getHeaders(): MutableMap<String, String> {
                    val requestHeaders = HashMap<String, String>()
                    val token_access = userSharedPreferences.getString("token", "")
                    requestHeaders["Authorization"] = "Bearer $token_access";
                    return requestHeaders

                }

                override fun getParams(): MutableMap<String, String> {
                    val m_params = HashMap<String, String>()
                    return m_params
                }
            }//end request register
        VolleySingleTon.getInstance(m_context).addToRequestQueue(str_request)
    }


    //add free course
    fun TakeFreeCourse(course_id: String, success: (message: String) -> Unit) {
        val str_request =
            object : StringRequest(
                Method.POST, RequestEndPoints.takeACourse + "/$course_id",
                { strResponse ->
                    val message = JSONObject(strResponse).getString("message").toString()
                    success(message)
                }
                //error
                , {
                    it.printStackTrace()
                }) {

                override fun getHeaders(): MutableMap<String, String> {
                    val requestHeaders = HashMap<String, String>()
                    val token_access = userSharedPreferences.getString("token", "")
                    requestHeaders["Authorization"] = "Bearer $token_access";
                    return requestHeaders
                }

                override fun getParams(): MutableMap<String, String> {
                    val m_params = HashMap<String, String>()
                    return m_params
                }
            }//end request register
        VolleySingleTon.getInstance(m_context).addToRequestQueue(str_request)
    }

    fun CheckCourseTaken(course_id: String, result: (favorite: Boolean) -> Unit) {
        val str_request =
            object : StringRequest(
                Method.POST, RequestEndPoints.checkCourseToken + "/$course_id",
                { strResponse ->
                    val jsonResponseObject = JSONObject(strResponse)
                    result(jsonResponseObject.getBoolean("status"))
                }
                //error
                , {
                    it.printStackTrace()
                }) {

                override fun getHeaders(): MutableMap<String, String> {
                    val requestHeaders = HashMap<String, String>()
                    val token_access = userSharedPreferences.getString("token", "")
                    requestHeaders["Authorization"] = "Bearer $token_access";
                    return requestHeaders

                }

                override fun getParams(): MutableMap<String, String> {
                    val m_params = HashMap<String, String>()
                    return m_params
                }
            }//end request register
        VolleySingleTon.getInstance(m_context).addToRequestQueue(str_request)
    }


    //course comments

    fun showCourseComment4(course_id: String, result: (courseComment: CourseComments) -> Unit) {
        val str_request =
            object : StringRequest(
                Method.POST, RequestEndPoints.all4Comment + "/$course_id",
                { strResponse ->
                    val courseComment =
                        Gson().fromJson(strResponse, CourseComments::class.java)
                    result(courseComment)
                }
                //error
                , {
                    it.printStackTrace()
                }) {

                override fun getHeaders(): MutableMap<String, String> {
                    val requestHeaders = HashMap<String, String>()
                    val token_access = userSharedPreferences.getString("token", "")
                    requestHeaders["Authorization"] = "Bearer $token_access";
                    return requestHeaders

                }

                override fun getParams(): MutableMap<String, String> {
                    val m_params = HashMap<String, String>()
                    return m_params
                }
            }//end request register
        VolleySingleTon.getInstance(m_context).addToRequestQueue(str_request)
    }

    fun showCourseComment(course_id: String, result: (courseComment: CourseComments) -> Unit) {
        val str_request =
            object : StringRequest(
                Method.POST, RequestEndPoints.allComments + "/$course_id",
                { strResponse ->
                    val courseComment =
                        Gson().fromJson(strResponse, CourseComments::class.java)
                    result(courseComment)
                }
                //error
                , {
                    it.printStackTrace()
                }) {

                override fun getHeaders(): MutableMap<String, String> {
                    val requestHeaders = HashMap<String, String>()
                    val token_access = userSharedPreferences.getString("token", "")
                    requestHeaders["Authorization"] = "Bearer $token_access";
                    return requestHeaders

                }

                override fun getParams(): MutableMap<String, String> {
                    val m_params = HashMap<String, String>()
                    return m_params
                }
            }//end request register
        VolleySingleTon.getInstance(m_context).addToRequestQueue(str_request)
    }

    fun addCourseComment(comment:String,course_id: String) {
        val str_request =
            object : StringRequest(
                Method.POST, RequestEndPoints.addComment + "/$course_id",
                { strResponse ->

                }
                //error
                , {
                    it.printStackTrace()
                }) {

                override fun getHeaders(): MutableMap<String, String> {
                    val requestHeaders = HashMap<String, String>()
                    val token_access = userSharedPreferences.getString("token", "")
                    requestHeaders["Authorization"] = "Bearer $token_access";
                    return requestHeaders

                }

                override fun getParams(): MutableMap<String, String> {
                    val m_params = HashMap<String, String>()
                    m_params["comment"]=comment
                    return m_params
                }
            }//end request register
        VolleySingleTon.getInstance(m_context).addToRequestQueue(str_request)
    }

    fun removeCourseComment(comment_id: String) {
        val str_request =
            object : StringRequest(
                Method.POST, RequestEndPoints.removeComment + "/$comment_id",
                { strResponse ->

                }
                //error
                , {
                    it.printStackTrace()
                }) {

                override fun getHeaders(): MutableMap<String, String> {
                    val requestHeaders = HashMap<String, String>()
                    val token_access = userSharedPreferences.getString("token", "")
                    requestHeaders["Authorization"] = "Bearer $token_access";
                    return requestHeaders

                }

                override fun getParams(): MutableMap<String, String> {
                    val m_params = HashMap<String, String>()

                    return m_params
                }
            }//end request register
        VolleySingleTon.getInstance(m_context).addToRequestQueue(str_request)
    }



}