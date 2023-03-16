package com.widsons.article.ui

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.widsons.article.data.model.Category
import com.widsons.article.domain.repository.ArticleRepository
import com.widsons.article.domain.repository.CategoryRepository
import com.widsons.article.domain.usecase.LoadArticleUseCaseImpl
import com.widsons.article.domain.usecase.LoadCategoryUseCase
import com.widsons.article.domain.usecase.LoadCategoryUseCaseImpl
import com.widsons.article.ui.home.HomeViewModel
import com.widsons.core.data.model.BaseResponse
import com.widsons.core.state.UIState
import com.widsons.core.utils.JsonProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    val articleRepository: ArticleRepository = mock()

    val categoryRepository: CategoryRepository = mock()

    val loadArticleUseCaseImpl = LoadArticleUseCaseImpl(articleRepository)

    val loadCategoryUseCaseImpl = LoadCategoryUseCaseImpl(categoryRepository)

    val homeViewModel: HomeViewModel =
        HomeViewModel(loadArticleUseCaseImpl, loadCategoryUseCaseImpl)

    fun `prepare article repository return success`() = runBlocking {
        given(articleRepository.getListArticle(null)).willAnswer {
            flow {
                emit(JsonProvider.resToObj<BaseResponse<List<Category>>>("/v1/article/list_success.json"))
            }
        }
    }

    fun `prepare article repository return empty`() = runBlocking {
        given(articleRepository.getListArticle(null)).willAnswer {
            flow {
                emit(JsonProvider.resToObj<BaseResponse<List<Category>>>("/v1/article/no_list_success.json"))
            }
        }
    }

    fun `prepare category repository return success`() = runBlocking {
        given(categoryRepository.getCategories()).willAnswer {
            flow {
                emit(JsonProvider.resToObj<BaseResponse<List<Category>>>("/v1/category/list_success_response.json"))
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test home view model initialLoadArticle return success`() = runTest {
        `prepare article repository return success`()
        `prepare category repository return success`()

        homeViewModel.listArticleStateFlow.test {
            Truth.assertThat(awaitItem()::class.java.toString()).isEqualTo(UIState.Loading::class.java.toString())

            homeViewModel.initialLoadArticle()

            Truth.assertThat(awaitItem()::class.java.toString()).isEqualTo(UIState.Success::class.java.toString())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test home view model initialLoadArticle return throw exception empty`() = runTest {
        `prepare article repository return empty`()
        `prepare category repository return success`()

        homeViewModel.listArticleStateFlow.test {
            Truth.assertThat(awaitItem()::class.java.toString()).isEqualTo(UIState.Loading::class.java.toString())

            homeViewModel.initialLoadArticle()

            Truth.assertThat(awaitItem()::class.java.toString()).isEqualTo(UIState.Error::class.java.toString())
        }
    }
}