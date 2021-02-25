package com.example.apikotlin.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        private var retrofit: Retrofit? = null

        fun getApiClient(): Retrofit {

            if (retrofit == null) {

                var inteceptor = HttpLoggingInterceptor()
                inteceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                var client = OkHttpClient.Builder().addInterceptor(inteceptor).build()
                retrofit = Retrofit.Builder()
                    .baseUrl("https://reqres.in/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
            }
            return retrofit!!
        }
    }
}