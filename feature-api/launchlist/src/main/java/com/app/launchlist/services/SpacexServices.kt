package com.app.launchlist.services

import com.app.launchlist.responses.SpacexLaunchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SpacexServices {
    @GET("launches")
    suspend fun getLaunches(): Response<List<SpacexLaunchResponse>>
}