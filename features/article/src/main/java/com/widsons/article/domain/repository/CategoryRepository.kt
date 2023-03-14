package com.widsons.article.domain.repository

import com.widsons.article.data.model.Category
import com.widsons.core.data.model.BaseResponse
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategories() : Flow<BaseResponse<List<Category>>>
}