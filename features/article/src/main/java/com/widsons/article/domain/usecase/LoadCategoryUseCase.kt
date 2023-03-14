package com.widsons.article.domain.usecase

import com.widsons.article.data.model.Category
import com.widsons.article.domain.repository.CategoryRepository
import com.widsons.core.interactor.NoParamUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton


typealias LoadCategoryUseCase = NoParamUseCase<Flow<List<Category>>>

@Singleton
class LoadCategoryUseCaseImpl @Inject constructor(
    val categoryRepository: CategoryRepository
): LoadCategoryUseCase {
    override suspend fun invoke() = categoryRepository.getCategories().onEach {
        if(it.error) {
            throw Exception(it.message)
        }
    }.map { it.details }
}