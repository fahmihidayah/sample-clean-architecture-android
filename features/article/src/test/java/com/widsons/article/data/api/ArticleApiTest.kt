package com.widsons.article.data.api

import com.widsons.article.data.model.Article
import com.widsons.article.data.utils.ScenarioArticleApiUtils
import com.widsons.core.data.model.BaseResponse
import com.widsons.core.utils.JsonProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.willReturn
import java.io.IOException


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ArticleApiTest {

    var articleAPI : ArticleAPI = mock()

    @Before
    fun setUp() {

    }

    fun `prepare request single article`() = runBlocking {
        given(articleAPI.getArticleDetail("1")).willAnswer {
            JsonProvider.resToObj<BaseResponse<Article>>("/v1/article/single_success.json")
        }
    }

    fun `prepare request single article failure`() = runBlocking {
        given(articleAPI.getArticleDetail("1")).willAnswer {
            JsonProvider.resToObj<BaseResponse<Article>>("/v1/article/failure.json")
        }
    }

    @Test
    fun `test article api request list api result success true`() = runTest {
        ScenarioArticleApiUtils.`prepare success response`(articleAPI)
        val response = articleAPI.getArticles()
        Assert.assertEquals(response.details.size, 8)
    }

    @Test
    fun `test article api request list api result failure true`() = runTest {
        ScenarioArticleApiUtils.`prepare failure response`(articleAPI)
        val response = articleAPI.getArticles()
        Assert.assertEquals(response.error, true)
    }

    @Test
    fun `test article api request list api result empty true`() = runTest {
        given(articleAPI.getArticles(keyword = "hardkeyword", categoryId = "12"))
            .willReturn {
                JsonProvider.resToObj("/v1/article/no_list_success.json")
            }
        val response = articleAPI.getArticles(keyword = "hardkeyword", categoryId = "12")
        Assert.assertEquals(response.details.size, 0)
    }

    @Test
    fun `test article api request list api result one true`() = runTest {
        given(articleAPI.getArticles(keyword = "habra", categoryId = "3"))
            .willReturn {
                JsonProvider.resToObj("/v1/article/result_one_success.json")
            }
        val response = articleAPI.getArticles(keyword = "habra", categoryId = "3")
        Assert.assertEquals(response.details.size, 1)
    }

    @Test
    fun `test article api request list api result throw exception true`() = runTest {
        ScenarioArticleApiUtils.`prepare failure response throw exception`(articleAPI)
        Assert.assertThrows(IOException::class.java) {
            runBlocking {
                val response = articleAPI.getArticles()
                Assert.fail("should throw IOException")
            }
        }
    }

    @Test
    fun `test article api request article detail success true`() = runTest {
        `prepare request single article`()
        val response = articleAPI.getArticleDetail("1")
        Assert.assertNotNull(response.details.title)
    }

    @Test
    fun `test article api request article detail not found true`() = runTest {
        `prepare request single article failure`()
        val response = articleAPI.getArticleDetail("1")
        Assert.assertTrue(response.error)
    }

    @After
    fun tearDown() {

    }
}