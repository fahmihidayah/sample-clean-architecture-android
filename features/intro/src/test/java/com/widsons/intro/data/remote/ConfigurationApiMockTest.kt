package com.widsons.intro.data.remote

import com.widsons.intro.utils.JsonProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
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
class ConfigurationApiMockTest {

    val configurationApi: ConfigurationApi = mock()

    @Before
    fun setUp() {

    }

    fun `prepare success response`() = runTest {
        given(configurationApi.getConfiguration())
            .willReturn {
                JsonProvider.reourceToObject("/v1/success_fine_response.json")
            }
    }

    fun `prepare failure response`() = runTest {
        given(configurationApi.getConfiguration())
            .willReturn {
                JsonProvider.reourceToObject(
                    "/v1/failure_response.json"
                )
            }
    }

    fun `prepare failure response throw exception`() = runTest {
        given(configurationApi.getConfiguration()).willAnswer { inv -> throw IOException() }
    }

    @Test
    fun `test configuration api success request true`() = runTest {
        `prepare success response`()
        val response = configurationApi.getConfiguration()
        Assert.assertEquals(response.details.size, 5)
    }

    @Test
    fun `test configuration api response success serializer true`() = runTest {
        `prepare success response`()

        val response = configurationApi.getConfiguration()
        Assert.assertNotNull(response.details[0].value)
    }

    @Test
    fun `test configuration api return exception true`() = runTest {
        `prepare failure response throw exception`()
        Assert.assertThrows(IOException::class.java) {
            runBlocking {
                val response = configurationApi.getConfiguration()
                Assert.fail("should throw IOException")
            }
        }
    }

    @Test
    fun `test configuration api return failure true`() = runTest {
        `prepare failure response`()
        val response = configurationApi.getConfiguration()
        Assert.assertTrue(response.error)
    }
}