package com.widsons.core.di

import com.widsons.core.BuildConfig
import com.widsons.core.data.local.AppManager
import com.widsons.core.data.remote.interceptor.AuthInterceptor
import com.widsons.core.data.remote.interceptor.AuthInterceptorImpl
import com.widsons.core.data.remote.interceptor.InterceptorCollections
import com.widsons.core.data.remote.interceptor.InterceptorCollectionsImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
class InterceptorNetworkModule {

    @Provides
    fun providesInterceptorCollections(authInterceptor: AuthInterceptor) : InterceptorCollections {
        return InterceptorCollectionsImpl(
            listOf(
                HttpLoggingInterceptor().setLevel(
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                ),
                authInterceptor
            )
        )
    }

    @Provides
    fun providesAuthInterceptor(appManager: AppManager) : AuthInterceptor {
        return AuthInterceptorImpl(appManager = appManager)
    }
}