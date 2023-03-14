package com.widsons.core.di

import com.widsons.core.data.local.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Provides
    fun provideAppManager(appSharedPreferences: AppSharedPreferences) : AppManager = AppManagerImpl(appSharedPreferences)

}