package ir.danialchoopan.danialtube.data.api.requests.course

import android.content.Context
import android.util.Log
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import ir.danialchoopan.danialtube.data.api.RequestEndPoints
import ir.danialchoopan.danialtube.data.api.VolleySingleTon
import ir.danialchoopan.danialtube.data.api.model.courseSearch.SearchCourseModel
import ir.danialchoopan.danialtube.data.api.model.courseShow.ShowCourse
import ir.danialchoopan.danialtube.data.api.model.moreBestSelling.MoreBestSellingCourseModel
import ir.danialchoopan.danialtube.data.api.model.moreMostPopular.MorePopularCourseModel
import ir.danialchoopan.danialtube.data.api.model.myCourses.MyCoursesModel
import ir.danialchoopan.danialtube.data.api.model.myFavouriteCourses.MyFavouriteCoursesModel

class CourseRequest(val m_context: Context) {
    val userSharedPreferences = m_context.getSharedPreferences("user_data", Context.MODE_PRIVATE)

    fun showCourse(course_id:String,resultRequest:(showCourse:ShowCourse)->Unit){
        val str_request_show_course =
            object : StringRequest(
                Method.GET, RequestEndPoints.showCourseById + "/"+course_id,
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

    fun searchCourse(searchQuery:String,resultRequest:(searchCourseRequest:SearchCourseModel)->Unit){
        val str_request_search_course =
            object : StringRequest(
                Method.GET, RequestEndPoints.searchCourse + "/"+searchQuery,
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

    fun MyCourses(resultRequest:(myCoursesModel:MyCoursesModel)->Unit){
        val str_request =
            object : StringRequest(
                Method.POST, RequestEndPoints.myCourse ,
                { strResponse ->
                    Log.d("str response my course ",strResponse)
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
                    val token_access=userSharedPreferences.getString("token","")
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

    fun MyFavoritesCourses(resultRequest:(myFavouriteCoursesModel:MyFavouriteCoursesModel)->Unit){
        val str_request =
            object : StringRequest(
                Method.POST, RequestEndPoints.courseFavorites ,
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
                    val token_access=userSharedPreferences.getString("token","")
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

    fun ShowMoreBestsellingCourses(resultRequest:(ShowMoreBestsellingCourses:MoreBestSellingCourseModel)->Unit){
        val str_request =
            object : StringRequest(
                Method.GET, RequestEndPoints.homePageMoreBestSelling ,
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

    fun ShowMorePopularCourses(resultRequest:(morePopularCourseModel:MorePopularCourseModel)->Unit){
        val str_request =
            object : StringRequest(
                Method.GET, RequestEndPoints.homePageMoreBestSelling ,
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



    //favorite

    fun AddToFavoriteCourses(success:()->Unit,course_id: String){
        val str_request =
            object : StringRequest(
                Method.POST, RequestEndPoints.addCourseFavorite+"/$course_id" ,
                { strResponse ->
                    try {
                        success()
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
                    val token_access=userSharedPreferences.getString("token","")
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


    fun RemoveFromFavoriteCourses(success:()->Unit,course_id: String){
        val str_request =
            object : StringRequest(
                Method.POST, RequestEndPoints.removeCourseFavorite+"/$course_id" ,
                { strResponse ->
                    try {
                        success()
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
                    val token_access=userSharedPreferences.getString("token","")
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