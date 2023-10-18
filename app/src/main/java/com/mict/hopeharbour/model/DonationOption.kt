package com.mict.hopeharbour.model

import com.google.gson.annotations.SerializedName

data class DonationOption(
    @SerializedName("amount")
    val amount: Int,
    @SerializedName("description")
    val description: String
)
