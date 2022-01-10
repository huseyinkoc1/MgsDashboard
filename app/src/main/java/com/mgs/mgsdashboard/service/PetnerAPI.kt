package com.mgs.mgsdashboard.service

import com.mgs.mgsdashboard.model.petnerApi.Petner
import retrofit2.Call
import retrofit2.http.GET

interface PetnerAPI {
    companion object {
        private const val Petner = "admin/screen_dashboard"
    }
    @GET(Petner)
    fun getData(): Call<Petner>

}