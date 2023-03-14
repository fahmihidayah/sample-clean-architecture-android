package com.widsons.article.data.utils

import com.widsons.article.data.api.ArticleAPI
import com.widsons.core.utils.JsonProvider
import kotlinx.coroutines.test.runTest
import org.mockito.kotlin.given
import org.mockito.kotlin.willReturn
import java.io.IOException

object ScenarioArticleApiUtils {

    fun `prepare success response`(api : ArticleAPI, resourceFile : String = "/v1/article/list_success.json") = runTest {
        given(api.getArticles())
            .willReturn {
                JsonProvider.resToObj(resourceFile)
            }
    }

    fun `prepare failure response`(api: ArticleAPI, resourceFile: String = "/v1/article/failure.json") = runTest {
        given(api.getArticles())
            .willReturn {
                JsonProvider.resToObj(
                    resourceFile
                )
            }
    }

    fun `prepare failure response throw exception`(api: ArticleAPI) = runTest {
        given(api.getArticles()).willAnswer { inv -> throw IOException() }
    }
}