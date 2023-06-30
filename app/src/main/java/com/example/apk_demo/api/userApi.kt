package com.example.apk_demo.api

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

private interface IUserApi{
    @Headers(
        "Accept: application/json"
    )
    @GET("/userInfo")
    abstract fun getUserInfo(): Call<HttpResModel<UserModel>?>?
    @POST("/login")
    abstract fun login(): Call<HttpResModel<LoginModel>?>?
}
private val api = Retrofit.Builder()
    .baseUrl("http://lool.date:7001")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(IUserApi::class.java)

data class UserModel(
    var name: String,
    var age: Short,
    var avatar: String
)

data class LoginModel(
    var lastLogin: String,
    var token: String
)

enum class LoginMethod(val value: String){
    UNAME("uname")
}


data class LoginReqModel(
    var loginMethod: LoginMethod,
    var payload: Any
)

object UserApi{
    fun getUserInfo(onSuccess: (data: UserModel?) -> Unit) {
        val call = api.getUserInfo();
        // return makeApi()
        call!!.enqueue(object: Callback<HttpResModel<UserModel>?> {
            override fun onResponse(call: Call<HttpResModel<UserModel>?>, response: Response<HttpResModel<UserModel>?>) {
                if(response.isSuccessful) {
                    val res = response.body()
                    Log.d("Main", "success!" + res.toString())
                    onSuccess(res?.data as UserModel)
                }
            }

            override fun onFailure(call: Call<HttpResModel<UserModel>?>, t: Throwable) {
                Log.e("Main", "Failed mate " + t.message.toString())
            }
        })
    }

    fun login(params: LoginReqModel, onSuccess: (data: LoginModel?) -> Unit) {
        val call = api.login();
        // return makeApi()
        call!!.enqueue(object: Callback<HttpResModel<LoginModel>?> {
            override fun onResponse(call: Call<HttpResModel<LoginModel>?>, response: Response<HttpResModel<LoginModel>?>) {
                if(response.isSuccessful) {
                    val res = response.body()
                    Log.d("Main", "success!" + res.toString())
                    onSuccess(res?.data as LoginModel)
                }
            }

            override fun onFailure(call: Call<HttpResModel<LoginModel>?>, t: Throwable) {
                Log.e("Main", "Failed mate " + t.message.toString())
            }
        })
    }
}

