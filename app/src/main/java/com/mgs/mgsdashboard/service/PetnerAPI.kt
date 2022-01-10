package com.mgs.mgsdashboard.service

import com.mgs.mgsdashboard.model.petnerApi.Petner
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import java.util.*

interface PetnerAPI {
    companion object {
        private const val Petner = "admin/screen_dashboard"
    }

    @GET(Petner)
    fun getData(): Single<Petner>

}