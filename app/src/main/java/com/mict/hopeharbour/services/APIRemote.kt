package com.mict.hopeharbour.services

import com.mict.hopeharbour.model.Projects
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface APIRemote {
    @Headers("Accept: application/json")
    @GET("/GetLinkedProjects")
    suspend fun getProjects(@Query("country") country: String): Response<Projects>
}