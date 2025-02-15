package com.yudistiro.weather

import android.app.Application
import com.yudistiro.di.HomeComponent
import com.yudistiro.di.HomeComponentProvider

class WeatherApp  : Application(), HomeComponentProvider {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun provideHomeComponent(): HomeComponent.Factory {
        return appComponent.homeComponent()
    }

}

