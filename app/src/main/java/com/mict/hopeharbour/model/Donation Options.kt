package model

import com.google.gson.annotations.SerializedName
import com.mict.hopeharbour.model.DonationOption

data class DonationOptions(
    @SerializedName("donationOption")
    val donationOption: List<DonationOption>
)
