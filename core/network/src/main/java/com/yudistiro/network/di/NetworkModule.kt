package com.yudistiro.network.di

import com.yudis.network.BuildConfig.BASE_URL
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
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
class   NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        val okHttpBuilder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpBuilder.addInterceptor(httpLoggingInterceptor)

        return Retrofit.Builder().baseUrl(BASE_URL)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpBuilder.build())
            .build()
    }

    @Singleton
    @Provides
    fun providesCountriesApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    @Singleton
    @Provides
    @BaseInterceptor
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

//    @Singleton
//    @Provides
//    @GeoCodeInterceptor
//    fun provideGeoCodeBaseUrl(): String = BuildConfig.BASE_URL_GEO_CODE

//    @Singleton
//    @Provides
//    fun provideRetrofitForMainApi(
//        @BaseInterceptor baseUrl: String
//    ): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .addCallAdapterFactory(NetworkResponseAdapterFactory())
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }

//    @Singleton
//    @Provides
//    fun provideRetrofitForGeoCodeApi(
//        okHttpClient: OkHttpClient,
//        @GeoCodeInterceptor geoCodeBaseUrl: String
//    ): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(geoCodeBaseUrl)
//            .addCallAdapterFactory(NetworkResponseAdapterFactory())
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(okHttpClient)
//            .build()
//    }



//    @Singleton
//    @Provides
//    fun provideWeatherApi(
//        @BaseInterceptor retrofit: Retrofit
//    ): WeatherApi {
//        return retrofit.create(WeatherApi::class.java)
//    }

//    @Singleton
//    @Provides
//    fun provideGeoCodeApi(
//        @GeoCodeInterceptor retrofit: Retrofit
//    ): GeocodeApi {
//        return retrofit.create(GeocodeApi::class.java)
//    }

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class GeoCodeInterceptor

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class BaseInterceptor
}