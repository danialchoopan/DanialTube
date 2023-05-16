package ir.danialchoopan.danialtube.data.api.requests.user

import android.content.Context
import android.util.Log
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import com.google.gson.JsonObject
import ir.danialchoopan.danialtube.data.api.RequestEndPoints
import ir.danialchoopan.danialtube.data.api.VolleySingleTon
import ir.danialchoopan.danialtube.data.api.model.RegisterUserResponse
import ir.danialchoopan.danialtube.data.api.model.User
import ir.danialchoopan.danialtube.data.api.model.userEditPassword.UserEditResponse
import org.json.JSONObject

class UserAuthRequest(val m_context: Context) {
    val userSharedPreferences = m_context.getSharedPreferences("user_data", Context.MODE_PRIVATE)

    fun registerUser(
        name: String,
        email: String,
        phone: String,
        password: String,
        resultRequest: (success: Boolean, registerResponse: RegisterUserResponse) -> Unit
    ) {
        val str_register_request =
            object : StringRequest(Method.POST, RequestEndPoints.userRegister,
                { strResponse ->
                    Log.d("register log ", strResponse)
                    try {
                        val registerResult =
                            Gson().fromJson(strResponse, RegisterUserResponse::class.java)
                        resultRequest(true, registerResult)

                        //saving user data on phone
                        userSharedPreferences.edit().also {
                            it.putInt("id", registerResult.user.id)
                            it.putString("token", registerResult.token)
                            it.putString("name", registerResult.user.name)
                            it.putString("email", registerResult.user.email)
                            it.putString("phone", registerResult.user.phoneNumber)
                            it.putString("has_login", "has_login")

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
                        //return false and empty user

                    }
                }
                //error
                , {
                    it.printStackTrace()
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

    fun loginUser(
        emailOrPhone: String,
        password: String,
        resultRequest: (success: Boolean, loginResponse: RegisterUserResponse) -> Unit
    ) {
        val str_login_request =
            object : StringRequest(Method.POST, RequestEndPoints.userLogin,
                { strResponse ->
                    Log.d("login log ", strResponse)
                    try {
                        val responseRequest =
                            Gson().fromJson(strResponse, RegisterUserResponse::class.java)
                        resultRequest(true, responseRequest)
                        //saving user data on phone
                        userSharedPreferences.edit().also {
                            it.putInt("id", responseRequest.user.id)
                            it.putString("token", responseRequest.token)
                            it.putString("name", responseRequest.user.name)
                            it.putString("email", responseRequest.user.email)
                            it.putString("phone", responseRequest.user.phoneNumber)
                            it.putString("has_login", "has_login")

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

                        //return false and empty  user
                    }
                }
                //error
                , {
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
                    it.printStackTrace()
                }) {

                override fun getParams(): MutableMap<String, String> {
                    val m_params = HashMap<String, String>()
                    m_params["emailOrPhone"] = emailOrPhone
                    m_params["password"] = password
                    return m_params
                }

            }//end request register
        VolleySingleTon.getInstance(m_context).addToRequestQueue(str_login_request)
    }

    fun logoutUser(
        resultRequest: (success: Boolean) -> Unit
    ) {
        val str_logout_request =
            object : StringRequest(Method.POST, RequestEndPoints.userLogout,
                { strResponse ->
                    try {
                        Log.d("logout user response 1365", strResponse)

                        resultRequest(true)
                        //saving user data on phone
                        userSharedPreferences.edit().clear().apply()

                    } catch (e: Exception) {

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

            }
        VolleySingleTon.getInstance(m_context).addToRequestQueue(str_logout_request)
    }


    //edit user email password

    fun userEditPasswordEdit(
        name: String,
        email: String,
        oldPassword: String,
        newPassword: String,
        success: () -> Unit,
        failed: () -> Unit
    ) {
        val str_request =
            object : StringRequest(Method.POST, RequestEndPoints.userEditPasswordEdit,
                { strResponse ->
                    try {
                        val responseRequest =
                            Gson().fromJson(strResponse, UserEditResponse::class.java)

                        userSharedPreferences.edit().also {
                            it.putInt("id", responseRequest.id)
                            it.putString("name", responseRequest.name)
                            it.putString("email", responseRequest.email)
                            it.putString("phone", responseRequest.phoneNumber)
                        }.apply()
                        if (responseRequest.email.isEmpty() || responseRequest.email == null) {
                            failed()
                        } else {
                            success()
                        }
                    } catch (e: Exception) {
                        failed()
                    }
                }
                //error
                , {
                    it.printStackTrace()
                    failed()

                }) {

                override fun getHeaders(): MutableMap<String, String> {
                    val requestHeaders = HashMap<String, String>()
                    val token_access = userSharedPreferences.getString("token", "")
                    requestHeaders["Authorization"] = "Bearer $token_access";
                    return requestHeaders

                }

                override fun getParams(): MutableMap<String, String> {
                    val m_params = HashMap<String, String>()
                    m_params["m_name"] = name
                    m_params["email"] = email
                    m_params["oldPassword"] = oldPassword
                    m_params["newPassword"] = newPassword
                    return m_params
                }

            }
        VolleySingleTon.getInstance(m_context).addToRequestQueue(str_request)
    }

    //end edit user email password


    //phone number requests

    fun requestSmsValidationCode(success: (message:String) -> Unit, failed: () -> Unit) {
        val str_request =
            object : StringRequest(Method.POST, RequestEndPoints.userRequestPhoneValidCode,
                { strResponse ->
                    try {
                        success(JSONObject(strResponse).getString("message"))
                    }catch (e : Exception){
                        failed()
                    }

                }
                //error
                , {
                    it.printStackTrace()
                    failed()

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

            }
        VolleySingleTon.getInstance(m_context).addToRequestQueue(str_request)
    }

    fun sendRequestSmsValidationCode(code:String,success: (message:String,status:Boolean) -> Unit, failed: () -> Unit) {
        val str_request =
            object : StringRequest(Method.POST, RequestEndPoints.userSendPhoneValidCode,
                { strResponse ->
                    try {
                        val jsonObject=JSONObject(strResponse)
                        success(jsonObject.getString("message"),jsonObject.getBoolean("status"))
                    }catch (e : Exception){
                        failed()
                    }

                }
                //error
                , {
                    it.printStackTrace()
                    failed()

                }) {

                override fun getHeaders(): MutableMap<String, String> {
                    val requestHeaders = HashMap<String, String>()
                    val token_access = userSharedPreferences.getString("token", "")
                    requestHeaders["Authorization"] = "Bearer $token_access";
                    return requestHeaders
                }

                override fun getParams(): MutableMap<String, String> {
                    val m_params = HashMap<String, String>()
                    m_params["code"]=code
                    return m_params
                }

            }
        VolleySingleTon.getInstance(m_context).addToRequestQueue(str_request)
    }

    fun checkPhoneNumberIfValid(success: (result:Boolean) -> Unit) {
        val str_request =
            object : StringRequest(Method.POST, RequestEndPoints.userCheckValidPhone,
                { strResponse ->
                        success(JSONObject(strResponse).getBoolean("status"))

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

            }
        VolleySingleTon.getInstance(m_context).addToRequestQueue(str_request)
    }
    //end phone requests


}