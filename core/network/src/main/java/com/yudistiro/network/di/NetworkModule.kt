package com.yudistiro.network.di

import com.yudistiro.common.BuildConfig
import com.yudistiro.network.api.GeocodeApi
import com.yudistiro.network.api.WeatherApi
import com.yudistiro.network.helper.NetworkResponseAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
class NetworkModule {


    @Provides
    fun provideOkHttpClient(
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        if (BuildConfig.DEBUG) {
                            setLevel(HttpLoggingInterceptor.Level.BODY)
                        }
                    },
            )
            .build()
    }

    @Singleton
    @Provides
    @BaseInterceptor
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

    @Singleton
    @Provides
    @GeoCodeInterceptor
    fun provideGeoCodeBaseUrl(): String = BuildConfig.BASE_URL_GEO_CODE

    @Singleton
    @Provides
    @BaseInterceptor
    fun provideRetrofitForMainApi(
        okHttpClient: OkHttpClient,
        @BaseInterceptor baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    @GeoCodeInterceptor
    fun provideRetrofitForGeoCodeApi(
        okHttpClient: OkHttpClient,
        @GeoCodeInterceptor geoCodeBaseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(geoCodeBaseUrl)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }



    @Singleton
    @Provides
    fun provideWeatherApi(
        @BaseInterceptor retrofit: Retrofit
    ): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    @Singleton
    @Provides
    fun provideGeoCodeApi(
        @GeoCodeInterceptor retrofit: Retrofit
    ): GeocodeApi {
        return retrofit.create(GeocodeApi::class.java)
    }

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class GeoCodeInterceptor

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class BaseInterceptor
}