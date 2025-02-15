package com.yudistiro.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yudistiro.common.di.ViewModelKey
import com.yudistiro.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class HomeViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

}