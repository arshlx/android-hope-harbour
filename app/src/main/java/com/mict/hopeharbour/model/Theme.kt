package com.mict.hopeharbour.model

import com.google.gson.annotations.SerializedName

data class Theme(
    @SerializedName("id")
    var id: String,
    @SerializedName("name")
    var name: String
)
