package com.widsons.article.di

import com.widsons.article.data.api.ArticleAPI
import com.widsons.article.data.api.CategoryApi
import com.widsons.article.data.repository.ArticleRepositoryImpl
import com.widsons.article.data.repository.CategoryRepositoryImpl
import com.widsons.article.domain.repository.ArticleRepository
import com.widsons.article.domain.repository.CategoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ArticleRepositoryModule {

    @Provides
    fun provideArticleRepository(articleAPI: ArticleAPI): ArticleRepository =
        ArticleRepositoryImpl(articleAPI)

    @Provides
    fun provideCategoryRepository(categoryApi: CategoryApi): CategoryRepository =
        CategoryRepositoryImpl(categoryApi)
}