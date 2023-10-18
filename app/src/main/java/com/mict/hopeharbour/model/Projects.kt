package com.mict.hopeharbour.model

import com.google.gson.annotations.SerializedName
import com.mict.hopeharbour.model.Project

class Projects {
    @SerializedName("project")
    var project: List<Project>? = null
}