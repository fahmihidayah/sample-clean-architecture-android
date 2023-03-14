package com.widsons.article.di

import com.widsons.article.data.api.ArticleAPI
import com.widsons.article.data.api.CategoryApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ArticleNetworkModule {
    @Provides
    fun providesArticleAPI(retrofit: Retrofit): ArticleAPI {
        return retrofit.create(ArticleAPI::class.java)
    }

    @Provides
    fun providesCategoryApi(retrofit: Retrofit) = retrofit.create(CategoryApi::class.java)
}