package com.yudistiro.weather

import com.yudistiro.di.HomeComponent
import dagger.Module

@Module(subcomponents =[ HomeComponent::class])
class SubcomponentsModule {}