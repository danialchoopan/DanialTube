package ir.danialchoopan.danialtube.data.api.requests

import android.content.Context
import android.util.Log
import com.android.volley.Request.Method
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import ir.danialchoopan.danialtube.data.api.RequestEndPoints
import ir.danialchoopan.danialtube.data.api.VolleySingleTon
import ir.danialchoopan.danialtube.data.api.model.RegisterUserResponse
import ir.danialchoopan.danialtube.data.api.model.User
import ir.danialchoopan.danialtube.data.api.model.homepage.HomePageRequestDataModel

class HomePageRequest(val m_context: Context) {
    fun homePage(resultRequest: (success: Boolean, homePageData: HomePageRequestDataModel) -> Unit) {
        val str_request_home_page =
            object : StringRequest(Method.GET, RequestEndPoints.homePage,
                { strResponse ->
                    Log.d("homePage log ", strResponse)
                    try {
                        val homePageRequestData =
                            Gson().fromJson(strResponse, HomePageRequestDataModel::class.java)
                        resultRequest(true, homePageRequestData)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                //error
                , {
                    it.printStackTrace()
                }) {

                override fun getParams(): MutableMap<String, String> {
                    val m_params = HashMap<String, String>()

                    return m_params
                }

            }//end request register
        VolleySingleTon.getInstance(m_context).addToRequestQueue(str_request_home_page)
    }

    fun homePage1(): HomePageRequestDataModel {
        var homePageRequestData = HomePageRequestDataModel()
        val str_request_home_page =
            object : StringRequest(Method.GET, RequestEndPoints.homePage,
                { strResponse ->
                    Log.d("homePage log ", strResponse)
                    try {
                        homePageRequestData =
                            Gson().fromJson(strResponse, HomePageRequestDataModel::class.java)

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                //error
                , {
                    it.printStackTrace()
                }) {

                override fun getParams(): MutableMap<String, String> {
                    val m_params = HashMap<String, String>()

                    return m_params
                }

            }//end request register
        VolleySingleTon.getInstance(m_context).addToRequestQueue(str_request_home_page)

        return homePageRequestData
    }
}