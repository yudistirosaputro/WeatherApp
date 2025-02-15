package com.yudistiro.di

import com.yudistiro.domain.di.DomainModule
import com.yudistiro.home.HomeFragment
import dagger.Component
import dagger.Subcomponent
import javax.inject.Singleton


@Subcomponent(
    modules = [
        HomeViewModelModule::class,
    ]
)
interface HomeComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }
    fun inject(homeFragment: HomeFragment)
}