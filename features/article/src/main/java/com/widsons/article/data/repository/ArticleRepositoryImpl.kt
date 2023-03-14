package com.widsons.article.data.repository

import com.widsons.article.data.api.ArticleAPI
import com.widsons.article.data.model.Article
import com.widsons.article.data.model.ArticleQuery
import com.widsons.article.domain.repository.ArticleRepository
import com.widsons.core.data.model.BaseResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ArticleRepositoryImpl
constructor(
    val articleAPI: ArticleAPI
) : ArticleRepository {
    override suspend fun getListArticle(articleQuery: ArticleQuery?) = flow {
        emit(articleAPI.getArticles(
            keyword = articleQuery?.keyword,
            categoryId = articleQuery?.categoryId
        ))
    }

    override suspend fun getArticleDetail(id: String) = flow {
        emit(articleAPI.getArticleDetail(id))
    }
}