package com.example.apikotlin.home.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apikotlin.R
import com.example.apikotlin.databinding.ActivityMainBinding
import com.example.apikotlin.home.data.Data
import com.example.apikotlin.home.Repo.Repo
import com.example.apikotlin.home.data.RequestRegister
import com.example.apikotlin.home.view_model.ApiViewmodel


class MainActivity : AppCompatActivity() {

    private lateinit var apiviewmodel: ApiViewmodel
    private lateinit var adapter: Adapter
    private val usersData = ArrayList<Data>()
    var binding: ActivityMainBinding? = null
    private var repo: Repo? = null
    private var requestRegister: RequestRegister? = null
    var page: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        apiviewmodel = ViewModelProviders.of(this)[ApiViewmodel::class.java]


        requestRegister?.apply {
            email = "eve.holt@reqres.in"
            password = "pistol"
        }
        apiviewmodel.registerUser(requestRegister)


        initRecylerview()
        getUserDataByPage(page)
        setObserver()

        binding?.btnLoadMore?.setOnClickListener {
            page += 1
            getUserDataByPage(page)
        }

    }

    private fun getUserDataByPage(page: Int) {
        apiviewmodel.getRepoApiData(page)
    }


    private fun setObserver() {                                     // observes apiLiveData
        apiviewmodel.apiLiveData?.observe(this, Observer {
            if (it != null) {
                //     tv.text = it.data.toString()

                if (page == 1) {
                    usersData.clear()
                }
                it.data?.let { it1 -> usersData.addAll(it1) }
                adapter.notifyDataSetChanged()
                if (it.data?.isEmpty() == true) {
                    binding?.btnLoadMore?.visibility = View.GONE
                }

            } else {
                Toast.makeText(this, "Failed Null", Toast.LENGTH_SHORT).show()
            }
        })

        apiviewmodel.userLiveData.observe(this, Observer {
            if (it != null) {
                Log.d("TAG", it.token.toString())
                Log.d("TAG", it.id.toString())
            }
        })
    }

    private fun initRecylerview() {                                 // initialize recycler view

        val rv: RecyclerView = binding?.rv ?: findViewById(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this)
        adapter = Adapter(this)
        adapter.setData(usersData)
        rv.adapter = adapter
    }

}