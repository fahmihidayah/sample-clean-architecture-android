package com.widsons.core.di

import com.widsons.core.BuildConfig
import com.widsons.core.data.remote.interceptor.AuthInterceptor
import com.widsons.core.data.remote.interceptor.InterceptorCollections
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    val TIME_OUT = 2L
    val BASE_URL = "http://192.168.100.147:8002/api/v1/"


    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient).build()
    }

    @Provides
    fun providesOkHttpClient(
        interceptorCollections: InterceptorCollections
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                interceptorCollections.getInterceptors().forEach {
                    addInterceptor(it)
                }
            }
            .callTimeout(TIME_OUT, TimeUnit.MINUTES)
            .writeTimeout(TIME_OUT, TimeUnit.MINUTES)
            .readTimeout(TIME_OUT, TimeUnit.MINUTES)
            .connectTimeout(TIME_OUT, TimeUnit.MINUTES).build()
    }
}