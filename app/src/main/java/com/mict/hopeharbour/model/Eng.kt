package com.example.example

import com.google.gson.annotations.SerializedName


data class Eng (

  @SerializedName("official" ) var official : String? = null,
  @SerializedName("common"   ) var common   : String? = null

)