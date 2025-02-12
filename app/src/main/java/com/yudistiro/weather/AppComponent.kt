package com.yudistiro.weather

import android.content.Context
import com.yudistiro.network.api.GeocodeApi
import com.yudistiro.network.api.WeatherApi
import com.yudistiro.network.di.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}