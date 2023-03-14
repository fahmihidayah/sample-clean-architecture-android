package com.widsons.intro.di

import com.widsons.intro.data.remote.ConfigurationApi
import com.widsons.intro.domain.repository.ConfigurationRepository
import com.widsons.intro.data.repository.ConfigurationRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ConfigurationRepositoryModule {

    @Provides
    fun providesConfigurationRepository(configurationApi: ConfigurationApi) : ConfigurationRepository {
        return ConfigurationRepositoryImpl(configurationApi)
    }

}