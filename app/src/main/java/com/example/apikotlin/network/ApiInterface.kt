package com.example.apikotlin.network

import com.example.apikotlin.home.data.ApiData
import com.example.apikotlin.home.data.RegisterResponse
import com.example.apikotlin.home.data.RequestRegister
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

//https://reqres.in/api/users?page=2&id=7
interface ApiInterface{

//    @GET("users")
//    fun getApiData(@Query("page") page : Int, @Query("id") id : Int) : Call<ApiData>

    @GET("users")
    fun getApiData(@Query("page") page : Int) : Call<ApiData>

    @POST("register")
    fun registerUser(@Body mRequestRegister: RequestRegister) : Call<RegisterResponse>

}

//object ApiService{
//    val  apiInstance : ApiInterface
//    init {
//        val retrofit : Retrofit = Retrofit.Builder()
//            .baseUrl("https://reqres.in/api/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        apiInstance = retrofit.create(ApiInterface::class.java)
//    }
//}