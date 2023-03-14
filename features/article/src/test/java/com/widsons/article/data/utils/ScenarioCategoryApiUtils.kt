package com.widsons.article.data.utils

import com.widsons.article.data.api.CategoryApi
import com.widsons.core.utils.JsonProvider
import kotlinx.coroutines.runBlocking
import org.mockito.kotlin.given
import org.mockito.kotlin.willReturn
import java.io.IOException

object ScenarioCategoryApiUtils {

    fun `prepare success response`(categoryApi: CategoryApi) = runBlocking {
        given(categoryApi.getCategory()).willReturn {
            JsonProvider.resToObj("/v1/category/list_success_response.json")
        }
    }

    fun `prepare failure response`(categoryApi: CategoryApi) = runBlocking {
        given(categoryApi.getCategory()).willReturn {
            JsonProvider.resToObj("/v1/category/failure_response.json")
        }
    }

    fun `prepare failure throw exception`(categoryApi: CategoryApi) = runBlocking {
        given(categoryApi).willAnswer { inv -> throw IOException("io exception")}
    }!!
}