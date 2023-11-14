package com.mict.hopeharbour.services

import com.mict.hopeharbour.model.CountriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface AllCountriesRemote {
    @Headers("Accept: application/json")
    @GET("v3.1/all")
    suspend fun getAllCountries(@Query("fields") fields: String
    ): Response<List<CountriesResponse>>
}