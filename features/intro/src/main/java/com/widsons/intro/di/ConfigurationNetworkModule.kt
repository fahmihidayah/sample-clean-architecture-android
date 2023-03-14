package com.widsons.intro.di

import com.widsons.intro.data.remote.ConfigurationApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ConfigurationNetworkModule {
    @Provides
    fun providesConfigurationApi(retrofit: Retrofit) = retrofit.create(ConfigurationApi::class.java)
}