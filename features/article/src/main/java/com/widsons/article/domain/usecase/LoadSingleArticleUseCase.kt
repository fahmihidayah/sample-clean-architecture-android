package com.widsons.article.domain.usecase

import com.widsons.article.data.model.Article
import com.widsons.article.domain.repository.ArticleRepository
import com.widsons.core.data.model.BaseResponse
import com.widsons.core.interactor.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

typealias LoadSingleArticleUseCase = BaseUseCase<String, Flow<Article>>

@Singleton
class LoadSingleArticleUseCaseImpl @Inject constructor(
    val articleRepository: ArticleRepository
) : LoadSingleArticleUseCase {
    override suspend fun invoke(params: String) = articleRepository.getArticleDetail(params).onEach {
        if(it.error) {
            throw Exception(it.message)
        }
    }.map { it.details }
}