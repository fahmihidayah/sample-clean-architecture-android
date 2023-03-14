package com.widsons.article.domain.repository

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.widsons.article.data.model.Category
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

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CategoryRepositoryTest {

    val categoryRepository : CategoryRepository = mock()

    @Before
    fun setUp() {

    }

    fun `prepare success response`() = runBlocking {
        given(categoryRepository.getCategories()).willAnswer {
            flow {
                emit(JsonProvider.resToObj<BaseResponse<List<Category>>>("/v1/category/list_success_response.json"))
            }
        }
    }

    fun `prepare failure response`() = runBlocking {
        given(categoryRepository.getCategories()).willAnswer {
            flow {
                emit(JsonProvider.resToObj<BaseResponse<List<Category>>>("/v1/category/failure_response.json"))
            }
        }
    }

    @Test
    fun `test category repository return success true`() = runTest {
        `prepare success response`()
        categoryRepository.getCategories().test {
            Truth.assertThat(awaitItem().details.size).isEqualTo(3)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `test category repository return failure true`() = runTest {
        `prepare failure response`()
        categoryRepository.getCategories().test {
            Truth.assertThat(awaitItem().error).isTrue()
            cancelAndIgnoreRemainingEvents()
        }
    }

    @After
    fun tearDown() {

    }
}