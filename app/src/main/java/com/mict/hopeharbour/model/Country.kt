package com.mict.hopeharbour.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name")
    var name: String,
    @SerializedName("iso3166CountryCode")
    var iso3166CountryCode: String
)