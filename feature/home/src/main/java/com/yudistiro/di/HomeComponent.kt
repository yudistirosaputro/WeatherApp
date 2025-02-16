package com.yudistiro.di

import com.yudistiro.home.HomeFragment
import com.yudistiro.search.SearchFragment
import dagger.Subcomponent


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
    fun inject(searchFragment: SearchFragment)
}