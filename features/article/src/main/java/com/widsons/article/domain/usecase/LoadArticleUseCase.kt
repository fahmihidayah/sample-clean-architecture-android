package com.widsons.article.domain.usecase

import com.widsons.article.data.model.Article
import com.widsons.article.data.model.ArticleQuery
import com.widsons.article.domain.repository.ArticleRepository
import com.widsons.core.interactor.BaseUseCase
import com.widsons.core.interactor.NoParamUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

typealias LoadArticleUseCase = BaseUseCase<ArticleQuery?, Flow<List<Article>>>

@Singleton
class LoadArticleUseCaseImpl
@Inject
constructor(
    val articleRepository: ArticleRepository
) : LoadArticleUseCase {

    override suspend fun invoke(params: ArticleQuery?) =
        articleRepository.getListArticle(articleQuery = params).onEach {
            if(it.error) {
                throw Exception(it.message)
            }
            if(it.details.isEmpty()) {
                throw Exception("No article found")
            }
        }.map { it.details }

}