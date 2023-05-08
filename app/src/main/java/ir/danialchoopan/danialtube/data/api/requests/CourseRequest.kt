package ir.danialchoopan.danialtube.data.api.requests

import android.content.Context
import android.util.Log
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import ir.danialchoopan.danialtube.data.api.RequestEndPoints
import ir.danialchoopan.danialtube.data.api.VolleySingleTon
import ir.danialchoopan.danialtube.data.api.model.courseShow.ShowCourse
import ir.danialchoopan.danialtube.data.api.model.homepage.HomePageDataModelRequest

class CourseRequest(val m_context: Context) {

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



}