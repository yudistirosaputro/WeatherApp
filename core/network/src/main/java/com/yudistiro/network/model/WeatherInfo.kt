package com.yudistiro.network.model

import com.google.gson.annotations.SerializedName


data class WeatherInfo (

  @SerializedName("dt"      ) var dt      : Int?               = null,
  @SerializedName("main"    ) var mainInfo    : MainInfo?              = MainInfo(),
  @SerializedName("weather" ) var weather : ArrayList<Weather> = arrayListOf(),
  @SerializedName("clouds"  ) var clouds  : Clouds?            = Clouds(),
  @SerializedName("wind"    ) var wind    : Wind?              = Wind(),
  @SerializedName("pop"     ) var pop     : Double?               = null,
  @SerializedName("sys"     ) var sys     : Sys?               = Sys(),
  @SerializedName("dt_txt"  ) var dtTxt   : String?            = null

)