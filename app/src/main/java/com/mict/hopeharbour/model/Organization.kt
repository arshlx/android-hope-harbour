package com.mict.hopeharbour.model

import com.google.gson.annotations.SerializedName

data class Organization(
 @SerializedName("id")
 val id: Int,
 @SerializedName("name")
 val name: String,
 @SerializedName("ein")
 val ein: String,
 @SerializedName("addressLine1")
 val addressLine1: String,
 @SerializedName("addressLine2")
 val addressLine2: String,
 @SerializedName("city")
 val city: String,
 @SerializedName("state")
 val state: String,
 @SerializedName("postal")
 val postal: String,
 @SerializedName("iso3166CountryCode")
 val iso3166CountryCode: String,
 @SerializedName("url")
 val url: String,
 @SerializedName("logoUrl")
 val logoUrl: String,
 @SerializedName("mission")
 val mission: String,
 @SerializedName("totalProjects")
 val totalProjects: Int,
 @SerializedName("activeProjects")
 val activeProjects: Int,
 @SerializedName("themes")
 val themes: Theme,
 @SerializedName("countries")
 val countries: Countries,
 @SerializedName("country")
 val country: String
)
