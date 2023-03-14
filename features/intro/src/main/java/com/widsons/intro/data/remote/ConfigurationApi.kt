package com.widsons.intro.data.remote

import com.widsons.core.data.model.BaseResponse
import com.widsons.intro.data.model.Configuration
import retrofit2.http.GET

interface ConfigurationApi {
    @GET("configurations")
    suspend fun getConfiguration() : BaseResponse<List<Configuration>>
}