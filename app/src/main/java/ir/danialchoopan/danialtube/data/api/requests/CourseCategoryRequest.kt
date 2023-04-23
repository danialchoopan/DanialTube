package ir.danialchoopan.danialtube.data.api.requests

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import ir.danialchoopan.danialtube.data.api.VolleySingleTon

class CourseCategoryRequest(val m_context:Context) {
    fun getCategories(resultRequest:()->Unit){
        val jsonRequest =
            JsonObjectRequest(
                Request.Method.GET, "", null,
                { jsonObj ->
//                    val showCategoryModel = Gson().fromJson(
//                        jsonObj.toString(),
//                        ShowCategoryRequestDataModel::class.java
//                    )
//                    resultRequest(showCategoryModel)
                }, {
                    it.printStackTrace()
                }
            )
        VolleySingleTon.getInstance(m_context).addToRequestQueue(jsonRequest)
    }
}