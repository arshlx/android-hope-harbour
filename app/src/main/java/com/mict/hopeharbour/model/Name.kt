package com.mict.hopeharbour.model

import com.example.example.NativeName
import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("common") var common: String,
    @SerializedName("official") var official: String,
    @SerializedName("nativeName") var nativeName: NativeName
)