package com.mict.hopeharbour.model

import com.google.gson.annotations.SerializedName


data class Hin(
    @SerializedName("official") var official: String? = null,
    @SerializedName("common") var common: String? = null
)