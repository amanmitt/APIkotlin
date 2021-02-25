package com.example.apikotlin.home.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apikotlin.home.data.ApiData
import com.example.apikotlin.home.Repo.Repo
import com.example.apikotlin.home.data.RegisterResponse
import com.example.apikotlin.home.data.RequestRegister

class ApiViewmodel : ViewModel() {

    private var repo: Repo? = null
    var apiLiveData: MutableLiveData<ApiData>
    var userLiveData: MutableLiveData<RegisterResponse>

    init {
        repo = Repo()
        apiLiveData = MutableLiveData()
        userLiveData = MutableLiveData()
    }

    fun getRepoApiData(page: Int) {
        repo?.ApiData(page, apiLiveData)
    }

    fun registerUser(mRequestRegister: RequestRegister?) {
        if (mRequestRegister != null) {
            repo?.registerUser(mRequestRegister, userLiveData)
        }
    }

}