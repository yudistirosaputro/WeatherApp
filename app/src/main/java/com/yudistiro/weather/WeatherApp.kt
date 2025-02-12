package com.yudistiro.weather

import android.app.Application

class WeatherApp  : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}

