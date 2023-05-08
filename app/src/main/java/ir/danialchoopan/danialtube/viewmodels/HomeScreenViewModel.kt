package ir.danialchoopan.danialtube.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ir.danialchoopan.danialtube.data.api.model.RegisterUserResponse
import ir.danialchoopan.danialtube.data.api.model.User
import ir.danialchoopan.danialtube.data.api.requests.user.UserAuthRequest

class HomeScreenViewModel() : ViewModel() {

    //home page
//    fun getHomePageData(
//        m_context: Context,
//        resultViewModel: (success: Boolean, homePageDataView: HomePageRequestDataModel) -> Unit
//    ) {
//        HomePageRequest(m_context).homePage { success, homePageData ->
//            resultViewModel(success, homePageData)
//        }
//    }


    //checking user has login
    private var getUserHasLogin by mutableStateOf(false)
    fun getUserHasLogin(m_context: Context): Boolean {
        val userSharedPreferences =
            m_context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val user_login_result = "has_login"
        userSharedPreferences.getString("has_login", "no_login") ?: "no_login"
        getUserHasLogin = user_login_result == "has_login"
        return getUserHasLogin
    }

    fun getUserLoginData(m_context: Context): User {
        val userSharedPreferences =
            m_context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val user = User(
            "",
            userSharedPreferences.getString("email", "").toString(),
            userSharedPreferences.getInt("id", 1),
            userSharedPreferences.getString("name", "").toString(),
            userSharedPreferences.getString("phone", "").toString(),
            ""
        )
        return user
    }

    fun setUserHasLogin(m_context: Context) {
        val userSharedPreferences =
            m_context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
        userSharedPreferences.edit().let {
            it.putString("has_login", "has_login")
        }.apply()
    }


    //user requests
    fun userRegisterRequest(
        m_context: Context,
        name: String,
        email: String,
        phone: String,
        password: String,
        result: (success: Boolean) -> Unit
    ) {
        UserAuthRequest(m_context)
            .registerUser(
                name, email, phone, password
            ) { success, registerResponse ->
                result(success)
            }
    }


    fun userLoginRequest(
        m_context: Context,
        emailOrPhone: String,
        password: String,
        result: (success: Boolean, loginResponse: RegisterUserResponse) -> Unit
    ) {
        UserAuthRequest(m_context).loginUser(emailOrPhone, password) { success, loginResponse ->
            result(success, loginResponse)
        }
    }

    //user logout
    fun userLoginRequest(
        m_context: Context,
    ) {
        UserAuthRequest(m_context).logoutUser()
    }

    fun userCheckPhoneValidation(m_context: Context) {

    }

}