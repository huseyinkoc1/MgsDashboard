package com.mgs.mgsdashboard.service

import com.mgs.mgsdashboard.model.avfast.Avfast
import io.reactivex.Single
import retrofit2.http.GET


interface AvFastAPI {
    companion object {
        private const val Avfast = "screen?token=DfP3BkL97zZZnRX1ecgAe8"
    }

    @GET(Avfast)
    fun getData(): Single<Avfast>


}