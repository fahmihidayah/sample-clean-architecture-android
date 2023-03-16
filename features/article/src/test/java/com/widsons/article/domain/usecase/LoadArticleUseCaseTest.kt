package com.widsons.article.domain.usecase

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.widsons.article.data.model.Article
import com.widsons.article.data.model.ArticleQuery
import com.widsons.article.domain.repository.ArticleRepository
import com.widsons.core.data.model.BaseResponse
import com.widsons.core.utils.JsonProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.willReturn

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LoadArticleUseCaseTest {

    val loadArticleUseCase : LoadArticleUseCase = mock()
    val emptyArticleQuery : ArticleQuery = ArticleQuery()

    @Before
    fun startUp() {

    }

    fun `prepare load article return success`() = runBlocking {
        given(loadArticleUseCase.invoke(emptyArticleQuery)).willReturn {
            flow {
                emit(JsonProvider.resToObj<BaseResponse<List<Article>>>("/v1/article/list_success.json").details)
            }
        }
    }

    fun `prepare load article return empty`() = runBlocking {
        given(loadArticleUseCase.invoke(emptyArticleQuery)).willReturn {
            flow {
                throw Exception("No article found")
                emit(JsonProvider.resToObj<BaseResponse<List<Article>>>("/v1/article/no_list_success.json").details)
            }
        }
    }

    @Test
    fun `test load article use case return success true`() = runTest {
        `prepare load article return success`()
        loadArticleUseCase.invoke(emptyArticleQuery).test {
            Truth.assertThat(awaitItem().size).isNotEqualTo(0)
            cancelAndIgnoreRemainingEvents()
        }
    }


    @Test
    fun `test load article use case return empty true`() = runTest {
        `prepare load article return empty`()
        loadArticleUseCase.invoke(emptyArticleQuery).test {
            Truth.assertThat(awaitError().message).isEqualTo("No article found")
            cancelAndIgnoreRemainingEvents()
        }
    }

    @After
    fun tearDown() {

    }
}