package com.widsons.article.domain.repository

import com.widsons.article.data.model.Article
import com.widsons.article.data.model.ArticleQuery
import com.widsons.core.data.model.BaseResponse
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    suspend fun getListArticle(
        articleQuery: ArticleQuery?
    ): Flow<BaseResponse<List<Article>>>

    suspend fun getArticleDetail(id: String): Flow<BaseResponse<Article>>
}