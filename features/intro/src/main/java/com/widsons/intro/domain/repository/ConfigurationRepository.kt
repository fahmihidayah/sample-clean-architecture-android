package com.widsons.intro.domain.repository

import com.widsons.core.data.model.BaseResponse
import com.widsons.intro.data.model.Configuration
import kotlinx.coroutines.flow.Flow

interface ConfigurationRepository {
    suspend fun getConfiguration() : Flow<BaseResponse<List<Configuration>>>
}