package com.mict.hopeharbour.main.vm

import com.mict.hopeharbour.model.CountriesResponse
import com.mict.hopeharbour.model.Project
import com.mict.hopeharbour.model.updates.UpdateEntry
import com.mict.hopeharbour.model.updates.UpdatesResponse
import com.mict.hopeharbour.services.APIRemote
import com.mict.hopeharbour.services.AllCountriesRemote
import com.mict.hopeharbour.services.CountriesAPIRemote
import com.mict.hopeharbour.services.RetrofitService
import global_objects.TaskStatus

class MainRepository {
    private val remote = RetrofitService.getClient()?.create(APIRemote::class.java)
    private val countriesRemote =
        RetrofitService.getCountriesClient()?.create(CountriesAPIRemote::class.java)
    private val allCountriesRemote =
        RetrofitService.getAllCountriesClient()?.create(AllCountriesRemote::class.java)

    suspend fun getCountries(countryName: String): Pair<Int, List<CountriesResponse>?> {
        return try {
            val response = countriesRemote?.getCountries(countryName)
            val countriesList = response?.body()?.toList()
            println("Response body" + response?.body())
            if (response?.isSuccessful == true && !countriesList.isNullOrEmpty()) {
                Pair(TaskStatus.SUCCESS, countriesList)
            } else if (countriesList.isNullOrEmpty()) {
                Pair(TaskStatus.EMPTY, listOf<CountriesResponse>())
            } else Pair(response.code(), listOf<CountriesResponse>())
        } catch (e: Exception) {
            e.printStackTrace()
            Pair(TaskStatus.FAILURE, null)
        }
    }

    suspend fun getAllCountries(): Pair<Int, List<CountriesResponse>?> {
        return try {
            val response = allCountriesRemote?.getAllCountries("name,cca2")
            val countriesList = response?.body()?.toList()
            println("Response body" + response?.body())
            if (response?.isSuccessful == true && !countriesList.isNullOrEmpty()) {
                Pair(TaskStatus.SUCCESS, countriesList)
            } else if (countriesList.isNullOrEmpty()) {
                Pair(TaskStatus.EMPTY, listOf())
            } else Pair(response.code(), listOf())
        } catch (e: Exception) {
            e.printStackTrace()
            Pair(TaskStatus.FAILURE, null)
        }
    }

    suspend fun getProjects(countryName: String): Pair<Int, List<Project>?> {
        return try {
            val response = remote?.getProjects(countryName)
            val projectList = response?.body()?.project
            if (response?.isSuccessful == true && !projectList.isNullOrEmpty()) {
                Pair(TaskStatus.SUCCESS, projectList)
            } else {
                Pair(TaskStatus.EMPTY, null)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Pair(TaskStatus.FAILURE, null)
        }
    }

    suspend fun getProjectUpdates(countryName: String): Pair<Int, List<UpdateEntry>?> {
        return try {
            val response = remote?.getProjectUpdates(countryName)
            val updateList = response?.body()?.updateEntries
            println("Response body" + response?.body())
            if (response?.isSuccessful == true && !updateList.isNullOrEmpty()) {
                Pair(TaskStatus.SUCCESS, updateList)
            } else if (updateList.isNullOrEmpty()) {
                Pair(TaskStatus.EMPTY, listOf())
            } else Pair(response.code(), listOf())
        } catch (e: Exception) {
            e.printStackTrace()
            Pair(TaskStatus.FAILURE, null)
        }
    }
}