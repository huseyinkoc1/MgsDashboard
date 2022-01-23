package com.mgs.mgsdashboard.service

import com.mgs.mgsdashboard.model.redmine.Redmine
import io.reactivex.Single
import retrofit2.http.GET

interface RedmineAPI {

    companion object {
        private const val Redmine = "storypoints"
    }

    @GET(Redmine)
    fun getData(): Single<Redmine>
}