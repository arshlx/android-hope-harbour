package com.mict.hopeharbour.model

import com.google.gson.annotations.SerializedName

data class Project(
    @SerializedName("id")
    val id: Int,
    @SerializedName("organization")
    val organization: Organization,
    @SerializedName("active")
    val active: Boolean,
    @SerializedName("title")
    val title: String,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("themeName")
    val themeName: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("iso3166CountryCode")
    val iso3166CountryCode: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("imageLink")
    val imageLink: String,
    @SerializedName("donationOptions")
    val donationOptions: DonationOptions,
    @SerializedName("type")
    val type: String,
    @SerializedName("themes")
    val themes: Themes
)
