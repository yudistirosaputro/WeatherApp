package com.yudistiro.network.model

import com.google.gson.annotations.SerializedName


data class ForecastWeatherResponse (

  @SerializedName("cod"     ) var cod     : String?         = null,
  @SerializedName("message" ) var message : Int?            = null,
  @SerializedName("cnt"     ) var cnt     : Int?            = null,
  @SerializedName("list"    ) var weatherInfo    : ArrayList<WeatherInfo> = arrayListOf(),
  @SerializedName("city"    ) var city    : City?           = City()

)