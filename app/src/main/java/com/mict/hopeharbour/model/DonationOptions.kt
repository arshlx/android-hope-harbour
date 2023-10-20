package com.mict.hopeharbour.model

import com.google.gson.annotations.SerializedName

data class DonationOptions(
    @SerializedName("donationOption")
    val donationOption: List<DonationOption>
)
