package com.widsons.intro.data.repository

import com.widsons.intro.data.remote.ConfigurationApi
import com.widsons.intro.domain.repository.ConfigurationRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConfigurationRepositoryImpl @Inject constructor(
    private val configurationApi: ConfigurationApi
) : ConfigurationRepository {

    override suspend fun getConfiguration() = flow {
        emit(configurationApi.getConfiguration())
    }
}