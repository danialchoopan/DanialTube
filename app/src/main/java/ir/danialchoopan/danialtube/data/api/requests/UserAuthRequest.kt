package ir.danialchoopan.danialtube.data.api.requests

import android.content.Context
import android.util.Log
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import ir.danialchoopan.danialtube.data.api.RequestEndPoints
import ir.danialchoopan.danialtube.data.api.VolleySingleTon
import ir.danialchoopan.danialtube.data.api.model.RegisterUserResponse
import ir.danialchoopan.danialtube.data.api.model.User

class UserAuthRequest(val m_context:Context) {
    val userSharedPreferences = m_context.getSharedPreferences("user_data", Context.MODE_PRIVATE)

    fun registerUser(
        name:String,
        email:String,
        phone:String,
        password:String,
        resultRequest:(success:Boolean,registerResponse:RegisterUserResponse)->Unit){
        val str_register_request = object : StringRequest(Method.POST, RequestEndPoints.userRegister,
            { strResponse ->
                Log.d("register log " , strResponse)
                try {
                    val registerResult =
                        Gson().fromJson(strResponse, RegisterUserResponse::class.java)
                    resultRequest(true, registerResult)
                    userSharedPreferences.edit().also {
                        it.putInt("id", registerResult.user.id)
                        it.putString("token", registerResult.token)
                        it.putString("name", registerResult.user.name)
                        it.putString("email", registerResult.user.email)
                        it.putString("phone", registerResult.user.phoneNumber)
                    }.apply()
                } catch (e: Exception) {
                    resultRequest(
                        false, RegisterUserResponse(
                            "",
                            false,
                            "",
                            User(
                                "",
                                "",
                                1,
                                "",
                                "",
                                "",
                            )
                        )
                    )
                }
            }
            //error
            , {
                it.printStackTrace()
            }) {

            override fun getParams(): MutableMap<String, String> {
                val m_params = HashMap<String, String>()
                m_params["name"] = name
                m_params["email"] = email
                m_params["phone_number"] = phone
                m_params["password"] = password
                return m_params
            }

        }//end request register
        VolleySingleTon.getInstance(m_context).addToRequestQueue(str_register_request)
    }

    fun loginUser(){

    }

    fun logoutUser(){

    }

    fun requestSmsValidationCode(){

    }

    fun sendRequestSmsValidationCode(){

    }

    fun checkSmsValidation(){

    }
}