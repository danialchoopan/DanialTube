package ir.danialchoopan.danialtube.data.api.requests.homePage

import android.content.Context
import android.util.Log
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import ir.danialchoopan.danialtube.data.api.RequestEndPoints
import ir.danialchoopan.danialtube.data.api.VolleySingleTon
import ir.danialchoopan.danialtube.data.api.model.homepage.HomePageDataModelRequest

class HomePageRequest(val m_context: Context) {
    fun homePage(resultRequest: (success: Boolean, homePageData: HomePageDataModelRequest) -> Unit,
                 failed:()->Unit
                 ) {
        val str_request_home_page =
            object : StringRequest(Method.GET, RequestEndPoints.homePage,
                { strResponse ->
                    Log.d("response is success 747352",strResponse)
                    try {
                        val homePageRequestData =
                            Gson().fromJson(strResponse, HomePageDataModelRequest::class.java)
                        resultRequest(true, homePageRequestData)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Log.d("response is success 747352","catch")
                        failed()
                    }
                }
                //error
                , {
                    it.printStackTrace()
                    Log.d("response is success 747352","error vollty $it")
                    failed()
                }) {

                override fun getParams(): MutableMap<String, String> {
                    val m_params = HashMap<String, String>()

                    return m_params
                }

            }//end request register
        VolleySingleTon.getInstance(m_context).addToRequestQueue(str_request_home_page)
    }

}