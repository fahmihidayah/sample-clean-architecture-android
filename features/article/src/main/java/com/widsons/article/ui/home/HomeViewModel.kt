package com.widsons.article.ui.home

import androidx.lifecycle.viewModelScope
import com.widsons.article.data.model.Article
import com.widsons.article.domain.model.ArticlesCategories
import com.widsons.article.domain.usecase.LoadArticleUseCaseImpl
import com.widsons.article.domain.usecase.LoadCategoryUseCase
import com.widsons.article.domain.usecase.LoadCategoryUseCaseImpl
import com.widsons.core.state.UIState
import com.widsons.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val loadArticleUseCase: LoadArticleUseCaseImpl,
    val loadCategoryUseCase: LoadCategoryUseCaseImpl
) : BaseViewModel() {

    private val _listArticleStateFlow: MutableStateFlow<UIState<ArticlesCategories>> =
        MutableStateFlow(UIState.Loading())
    val listArticleStateFlow: StateFlow<UIState<ArticlesCategories>> = _listArticleStateFlow

    fun initialLoadArticle() {
        viewModelScope.launch {
            loadArticleUseCase
                .invoke(null)
                .zip(loadCategoryUseCase.invoke()) { articles, categories ->
                    ArticlesCategories(
                        articles = articles,
                        categories = categories
                    )
                }
                .catch {
                    _listArticleStateFlow.value = UIState.Error("Ops got an exception")
                }.collect {
                    _listArticleStateFlow.value = UIState.Success(it)
                }
        }
    }

}