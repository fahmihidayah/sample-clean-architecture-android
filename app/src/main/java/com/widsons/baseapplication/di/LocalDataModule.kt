package com.widsons.baseapplication.di

import com.widsons.baseapplication.BuildConfig
import com.widsons.core.data.local.BuildManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Provides
    fun providesBuildManager() = object :  BuildManager() {
        override val buildConfig: String
            get() = BuildConfig.VERSION_NAME
    }
}