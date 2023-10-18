package com.example.example

import com.google.gson.annotations.SerializedName


data class NativeName (

  @SerializedName("eng" ) var eng : Eng? = Eng(),
  @SerializedName("hin" ) var hin : Hin? = Hin(),
  @SerializedName("tam" ) var tam : Tam? = Tam()

)