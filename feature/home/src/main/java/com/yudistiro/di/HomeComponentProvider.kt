package com.yudistiro.di


interface HomeComponentProvider {
    fun provideHomeComponent() : HomeComponent.Factory
}