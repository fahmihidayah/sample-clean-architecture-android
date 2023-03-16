package com.widsons.article.ui.home

import androidx.lifecycle.viewModelScope
import com.widsons.article.data.model.Article
import com.widsons.article.data.model.ArticleQuery
import com.widsons.article.data.model.Category
import com.widsons.article.domain.model.ArticlesCategories
import com.widsons.article.domain.usecase.LoadArticleUseCaseImpl
import com.widsons.article.domain.usecase.LoadCategoryUseCase
import com.widsons.article.domain.usecase.LoadCategoryUseCaseImpl
import com.widsons.core.state.UIState
import com.widsons.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val loadArticleUseCase: LoadArticleUseCaseImpl,
    val loadCategoryUseCase: LoadCategoryUseCaseImpl
) : BaseViewModel() {

    private val _listArticleStateFlow: MutableStateFlow<UIState<List<Article>>> =
        MutableStateFlow(UIState.Loading())
    val listArticleStateFlow: StateFlow<UIState<List<Article>>> = _listArticleStateFlow


    private val _listCategoryStateFlow: MutableStateFlow<UIState<List<Category>>> =
        MutableStateFlow(UIState.Loading())
    val listCategoryStateFlow: StateFlow<UIState<List<Category>>> = _listCategoryStateFlow

    val articleCategory = ArticlesCategories(
        articles = listOf(),
        categories = listOf()
    )

    fun initialLoadArticle() {
        viewModelScope.launch {
            loadArticleUseCase
                .invoke(null)
                .catch { ex ->
                    _listArticleStateFlow.value = UIState.Error(ex.message)
                }.collect {
                    _listArticleStateFlow.value = UIState.Success(it)
                }
            loadCategoryUseCase.invoke().catch {
                _listCategoryStateFlow.value = UIState.Error(it.message)
            }.collect {
                _listCategoryStateFlow.value = UIState.Success(it)
            }
        }
    }

    fun loadArticleByCategory(category: Category) {
        viewModelScope.launch {
            loadArticleUseCase.invoke(params = ArticleQuery(categoryId = category.pk?.toString()))
                .catch {
                    _listArticleStateFlow.value = UIState.Error(it.message)
                }.onCompletion {

                }.collect {
                    _listArticleStateFlow.value = UIState.Success(it)
                }
        }
    }

}