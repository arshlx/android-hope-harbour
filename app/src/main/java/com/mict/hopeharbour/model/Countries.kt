package com.mict.hopeharbour.model

import com.google.gson.annotations.SerializedName

data class Countries(
    @SerializedName("country")
    val country: List<Country>
)
