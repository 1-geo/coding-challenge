package com.example.a20180101_mm_nycschools.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class Api {
    companion object {
        val API_BASE_PATH = "https://dog.ceo"
        private val TAG = "--ApiService"

        val apiCall = Retrofit.Builder()
        .baseUrl(API_BASE_PATH)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(Client.gsonConverter)
        .client(Client.client)
        .build()
        .create(ApiService::class.java)!!
    }
}


