package com.example.apk_demo.api

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

private interface IUserApi{
    @Headers(
        "Accept: application/json"
    )
    @GET("/userInfo")
    abstract fun getUserInfo(): Call<UserResModel?>?
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
data class UserResModel(
    var data: UserModel,
    var code: Int,
    var message: String
)

fun getUserInfo(onSuccess: (data: UserModel?) -> Unit) {
    val call: Call<UserResModel?>? = api.getUserInfo();
    call!!.enqueue(object: Callback<UserResModel?> {
        override fun onResponse(call: Call<UserResModel?>, response: Response<UserResModel?>) {
            if(response.isSuccessful) {
                val res = response.body()
                Log.d("Main", "success!" + res.toString())
                onSuccess(res?.data as UserModel)
            }
        }

        override fun onFailure(call: Call<UserResModel?>, t: Throwable) {
            Log.e("Main", "Failed mate " + t.message.toString())
        }
    })
}
