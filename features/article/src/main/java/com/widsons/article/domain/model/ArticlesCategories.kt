package com.widsons.article.domain.model

import com.widsons.article.data.model.Article
import com.widsons.article.data.model.Category

class ArticlesCategories(
    val articles : List<Article>,
    val categories: List<Category>
)