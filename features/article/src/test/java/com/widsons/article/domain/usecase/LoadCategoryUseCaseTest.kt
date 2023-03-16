package com.widsons.article.domain.usecase

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
import org.mockito.kotlin.willReturn

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LoadCategoryUseCaseTest {

    val loadCategoryUseCase: LoadCategoryUseCase = mock()

    @Before
    fun setUp() {

    }

    fun `prepare load category response success`() = runBlocking {
        given(loadCategoryUseCase.invoke()).willReturn {
            flow {
                emit(JsonProvider.resToObj<BaseResponse<List<Category>>>("/v1/category/list_success_response.json").details)
            }
        }
    }

    fun `prepare load category response failure`() = runBlocking {
        given(loadCategoryUseCase.invoke()).willReturn {
            flow {
                throw Exception("category not found")
                emit(JsonProvider.resToObj<BaseResponse<List<Category>>>("/v1/category/list_success_response.json").details)
            }
        }
    }

    @Test
    fun `test load category usecase response success true`() = runTest {
        `prepare load category response success`()
        loadCategoryUseCase.invoke().test {
            Truth.assertThat(awaitItem().size).isEqualTo(3)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `test load category usecase response failure true`() = runTest {
        `prepare load category response failure`()
        loadCategoryUseCase.invoke().test {
            Truth.assertThat(awaitError().message).isEqualTo("category not found")
            cancelAndIgnoreRemainingEvents()
        }
    }

    @After
    fun tearDown() {

    }

}