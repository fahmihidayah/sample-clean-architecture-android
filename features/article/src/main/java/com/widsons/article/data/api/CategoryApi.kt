package com.widsons.article.data.api

import com.widsons.article.data.model.Category
import com.widsons.core.data.model.BaseResponse
import retrofit2.http.GET

interface CategoryApi {
    @GET("categories")
    suspend fun getCategory() : BaseResponse<List<Category>>
}