package com.mict.hopeharbour.services

import com.mict.hopeharbour.model.CountriesResponse
import com.mict.hopeharbour.model.Name
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface CountriesAPIRemote {
    @Headers("Accept: application/json")
    @GET("{name}")
    suspend fun getCountries(
        @Path("name") requestBody: String,
        @Query("fields") field: String = "name"
    ): Response<List<CountriesResponse>>
}
