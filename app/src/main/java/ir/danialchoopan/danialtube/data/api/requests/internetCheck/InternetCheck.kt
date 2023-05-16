package ir.danialchoopan.danialtube.data.api.requests.internetCheck

import android.content.Context
import com.android.volley.toolbox.StringRequest
import ir.danialchoopan.danialtube.data.api.RequestEndPoints
import ir.danialchoopan.danialtube.data.api.VolleySingleTon

class InternetCheck(val m_context:Context) {
    fun checkConnection(success:()->Unit,failed:()->Unit){
        val str_request =
            object : StringRequest(
                Method.GET, RequestEndPoints.internetCheck ,
                { strResponse ->
                    success()
                }
                //error
                , {
                    failed()
                }) {
            }//end request register
        VolleySingleTon.getInstance(m_context).addToRequestQueue(str_request)
    }
}