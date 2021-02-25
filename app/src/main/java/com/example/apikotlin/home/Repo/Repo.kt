package com.example.apikotlin.home.Repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apikotlin.home.data.ApiData
import com.example.apikotlin.home.data.RegisterResponse
import com.example.apikotlin.home.data.RequestRegister
import com.example.apikotlin.network.ApiClient
import com.example.apikotlin.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repo {
    var apiInterface: ApiInterface? = null

    init {
        apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
    }

    fun ApiData(page: Int, apiLiveData: MutableLiveData<ApiData>) {

        apiInterface?.getApiData(page)?.enqueue(object : Callback<ApiData> {

            override fun onResponse(call: Call<ApiData>, response: Response<ApiData>) {
                apiLiveData.value = response.body()
            }

            override fun onFailure(call: Call<ApiData>, t: Throwable) {
                apiLiveData.value = null
            }
        })

    }

    fun registerUser(
        mRequestRegister: RequestRegister,
        userLiveData: MutableLiveData<RegisterResponse>
    ) {

        apiInterface?.registerUser(mRequestRegister)?.enqueue(object : Callback<RegisterResponse> {

            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                userLiveData.value = response.body()
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                userLiveData.value = null
            }

        })
    }
}


