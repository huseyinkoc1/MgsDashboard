package com.mgs.mgsdashboard.service

import com.google.gson.GsonBuilder
import com.mgs.mgsdashboard.model.avfastApi.Avfast
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class AvfastService {


        private val BASE_URL = "https://staging-api.avfast.site/"
        private val PATH_NAME = "api/v1/"
        private val DOMAIN_URL = BASE_URL+PATH_NAME

        var gson = GsonBuilder()
            .setLenient()
            .create()

    private val api =
        Retrofit.Builder()
            .baseUrl(DOMAIN_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(AvFastAPI::class.java)

    fun getData(): Single<Avfast> {
        return api.getData()
    }


}