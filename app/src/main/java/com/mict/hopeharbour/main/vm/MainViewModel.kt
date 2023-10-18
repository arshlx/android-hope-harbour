package com.mict.hopeharbour.main.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mict.hopeharbour.model.CountriesResponse
import com.mict.hopeharbour.model.Project
import global_objects.TaskStatus
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MainRepository()
    val countriesStatus = MutableLiveData(TaskStatus.NONE)
    var countriesList = listOf<CountriesResponse>()
    val projectStatus = MutableLiveData(TaskStatus.NONE)
    var projectList = listOf<Project>()
    var countryName = ""

    fun getCountries(countryName: String) {
        countriesStatus.value = TaskStatus.LOADING
        viewModelScope.launch {
            val result = repository.getCountries(countryName)
            countriesList = result.second!!
            countriesStatus.value = result.first
        }
    }

    fun getProjects() {
        projectStatus.value = TaskStatus.LOADING
        viewModelScope.launch {
            val result = repository.getProjects(countryName)
            if (result.second != null)
                projectList = result.second!!
            projectStatus.value = result.first
        }
    }
}