package com.widsons.article.data.api

import com.widsons.article.data.utils.ScenarioCategoryApiUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CategoryApiTest {

    val categoryApi : CategoryApi = mock()

    @Before
    fun setUp() {

    }

    @Test
    fun `test category api success response true`() = runTest {
        ScenarioCategoryApiUtils.`prepare success response`(categoryApi)
        val response = categoryApi.getCategory()
        Assert.assertEquals(response.details.size, 3)
    }

    @Test
    fun `test category api failure response true`() = runTest {
        ScenarioCategoryApiUtils.`prepare failure response`(categoryApi)
        val response = categoryApi.getCategory()
        Assert.assertTrue(response.error)
    }

    @After
    fun tearDown() {

    }
}