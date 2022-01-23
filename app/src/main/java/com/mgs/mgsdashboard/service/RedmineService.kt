package com.mgs.mgsdashboard.service

import com.google.gson.GsonBuilder
import com.mgs.mgsdashboard.model.redmine.Redmine
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RedmineService {

    private val BASE_URL = "http://mgssoftware.net:5002/"

    var gson = GsonBuilder()
        .setLenient()
        .create()

    private val api =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(clientSetup)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(RedmineAPI::class.java)

    fun getData(): Single<Redmine> {
        return api.getData()
    }

}

val clientSetup = OkHttpClient.Builder()
    .connectTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30,TimeUnit.SECONDS)
    .build()