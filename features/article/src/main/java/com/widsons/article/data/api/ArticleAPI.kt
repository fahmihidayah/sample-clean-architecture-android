package com.widsons.article.data.api

import com.widsons.article.data.model.Article
import com.widsons.core.data.model.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticleAPI {

    @GET("articles")
    suspend fun getArticles(
        @Query("keyword") keyword : String? = null,
        @Query("category_id") categoryId : String? = null
    ) : BaseResponse<List<Article>>

    @GET("article/{id}")
    suspend fun getArticleDetail(@Path("id") id : String) : BaseResponse<Article>
}