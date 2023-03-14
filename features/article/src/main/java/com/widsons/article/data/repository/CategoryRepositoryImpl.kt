package com.widsons.article.data.repository

import com.widsons.article.data.api.CategoryApi
import com.widsons.article.data.model.Category
import com.widsons.article.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CategoryRepositoryImpl
    constructor(
        val categoryApi: CategoryApi
    ): CategoryRepository {
    override suspend fun getCategories() = flow {
        emit(categoryApi.getCategory())
    }
}