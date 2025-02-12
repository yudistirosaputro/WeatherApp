package com.yudistiro.network.di

import com.yudistiro.common.BuildConfig
import com.yudistiro.network.api.GeocodeApi
import com.yudistiro.network.api.WeatherApi
import com.yudistiro.network.helper.NetworkResponseAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

    @Singleton
    @Provides
    @Named("GeoCodeUrl")
    fun provideGeoCodeBaseUrl(): String = BuildConfig.BASE_URL_GEO_CODE

    @Singleton
    @Provides
    fun provideOkHttpClient(
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    if (BuildConfig.DEBUG) {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                }
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitForMainApi(
        okHttpClient: OkHttpClient,
        @Named("BaseUrl") baseUrl: String
    ): Retrofit {
        return createRetrofit(baseUrl, okHttpClient)
    }

    @Singleton
    @Provides
    fun provideRetrofitForGeoCodeApi(
        okHttpClient: OkHttpClient,
        @Named("GeoCodeUrl") geoCodeBaseUrl: String
    ): Retrofit {
        return createRetrofit(geoCodeBaseUrl, okHttpClient)
    }

    private fun createRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideWeatherApi(
        @Named("BaseUrl") retrofit: Retrofit
    ): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    @Singleton
    @Provides
    fun provideGeoCodeApi(
        @Named("GeoCodeUrl") retrofit: Retrofit
    ): GeocodeApi {
        return retrofit.create(GeocodeApi::class.java)
    }
}