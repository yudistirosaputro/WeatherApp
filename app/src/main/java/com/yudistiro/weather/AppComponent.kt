package com.yudistiro.weather

import android.content.Context
import com.yudistiro.data.di.DataModule
import com.yudistiro.di.HomeComponent
import com.yudistiro.domain.di.DomainModule
import com.yudistiro.local.di.LocalModule
import com.yudistiro.network.di.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        LocalModule::class,
        DataModule::class,
        DomainModule::class,
        SubcomponentsModule::class
    ]
)
interface AppComponent {
    fun inject(activity: MainActivity)
    fun homeComponent() : HomeComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}
