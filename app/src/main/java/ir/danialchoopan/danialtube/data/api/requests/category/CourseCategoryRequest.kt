package ir.danialchoopan.danialtube.data.api.requests.category

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import ir.danialchoopan.danialtube.data.api.RequestEndPoints
import ir.danialchoopan.danialtube.data.api.VolleySingleTon
import ir.danialchoopan.danialtube.data.api.model.courseShowCategory.ShowCategoriesCourse
import ir.danialchoopan.danialtube.data.api.model.myFavouriteCourses.MyFavouriteCoursesModel
import ir.danialchoopan.danialtube.data.api.model.showCategory.CourseCategory
import ir.danialchoopan.danialtube.data.api.model.showCategory.ShowCategory
import ir.danialchoopan.danialtube.data.api.model.showSubCategory.ShowSubCategory

class CourseCategoryRequest(val m_context:Context) {

    fun getCategories(resultRequest:(courseCategory: ShowCategory)->Unit){
        val str_request =
        object : StringRequest(
            Method.GET, RequestEndPoints.courseCategory ,
            { strResponse ->
                try {
                    val courseCategory =
                        Gson().fromJson(strResponse, ShowCategory::class.java)
                    resultRequest(courseCategory)

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

    fun getSubCategories(categoryId:String,resultRequest:(showSubCategory:ShowSubCategory)->Unit){
        val str_request =
        object : StringRequest(
            Method.GET, RequestEndPoints.subCourseCategory +"/$categoryId",
            { strResponse ->
                try {
                    val showSubCategory =
                        Gson().fromJson(strResponse, ShowSubCategory::class.java)
                    resultRequest(showSubCategory)

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


    fun getSubCategoryCourse(subCategoryId:String,resultRequest:(showCategoriesCourse:ShowCategoriesCourse)->Unit){
        val str_request =
        object : StringRequest(
            Method.GET, RequestEndPoints.subCourseCategoryCourses +"/$subCategoryId",
            { strResponse ->
                try {
                    val showCategoriesCourse =
                        Gson().fromJson(strResponse, ShowCategoriesCourse::class.java)
                    resultRequest(showCategoriesCourse)

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

}