package com.widsons.article.domain.repository

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.widsons.article.data.model.Article
import com.widsons.article.data.model.ArticleQuery
import com.widsons.core.data.model.BaseResponse
import com.widsons.core.utils.JsonProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.willAnswer
import org.mockito.kotlin.willThrow
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ArticleRepositoryTest {

    var articleRepository: ArticleRepository = mock()

    var emptyArticleQuery: ArticleQuery = ArticleQuery()

    var articleQuery : ArticleQuery = ArticleQuery(categoryId = "1", keyword = "test")

    @Before
    fun setUp() {

    }

    fun `prepare success response single article`() = runBlocking {
        given(articleRepository.getArticleDetail("1")).willAnswer {
            flow {
                emit(JsonProvider.resToObj<BaseResponse<Article>>("/v1/article/single_success.json"))
            }
        }
    }

    fun `prepare failure response single article`() = runBlocking {
        given(articleRepository.getArticleDetail("1")).willAnswer {
            flow {
                emit(JsonProvider.resToObj<BaseResponse<Article>>("/v1/article/failure.json"))
            }
        }
    }

    fun `prepare success response`() = runBlocking {
        given(articleRepository.getListArticle(emptyArticleQuery)).willAnswer {
            flow {
                emit(JsonProvider.resToObj<BaseResponse<List<Article>>>("/v1/article/list_success.json"))
            }
        }
    }


    fun `prepare success with article query response`() = runBlocking {
        given(articleRepository.getListArticle(articleQuery)).willAnswer {
            flow {
                emit(JsonProvider.resToObj<BaseResponse<List<Article>>>("/v1/article/list_success.json"))
            }
        }
    }

    fun `prepare failure response`() = runBlocking {
        given(articleRepository.getListArticle(emptyArticleQuery)).willAnswer {
            flow {
                emit(JsonProvider.resToObj<BaseResponse<List<Article>>>("/v1/article/failure.json"))
            }
        }
    }

    fun `prepare failure throw exception respose`() = runBlocking {
        given(articleRepository.getListArticle(emptyArticleQuery)).willAnswer {
            flow {
                throw IOException("exception")
                emit(JsonProvider.resToObj<BaseResponse<List<Article>>>("/v1/article/failure.json"))
//                emit(IOException("exception"))
            }
        }
    }

    @Test
    fun `test article repository return success true`() = runTest {
        `prepare success response`()
        articleRepository.getListArticle(emptyArticleQuery).test {
            Truth.assertThat(awaitItem().details.size).isEqualTo(8)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `test article repository filter with article query return success true`() = runTest {
        `prepare success with article query response`()
        articleRepository.getListArticle(articleQuery).test {
            Truth.assertThat(awaitItem().details.size).isEqualTo(8)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `test article repository return failure true`() = runTest {
        `prepare failure response`()
        articleRepository.getListArticle(emptyArticleQuery).test {
            Truth.assertThat(awaitItem().error).isTrue()
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `test article repository return throw ioexception true`() = runTest {
        `prepare failure throw exception respose`()
        articleRepository.getListArticle(emptyArticleQuery).test {
            Truth.assertThat(awaitError().toString()).contains("IOException")
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `test article repository get single article return success`() = runTest {
        `prepare success response single article`()
        articleRepository.getArticleDetail("1").test {
            Truth.assertThat(awaitItem().details.title).isNotNull()
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `test article repository get single article return failure true`() = runTest {
        `prepare failure response single article`()
        articleRepository.getArticleDetail("1").test {
            Truth.assertThat(awaitItem().details).isNull()
            cancelAndIgnoreRemainingEvents()
        }
    }


    @After
    fun tearDown() {

    }
}