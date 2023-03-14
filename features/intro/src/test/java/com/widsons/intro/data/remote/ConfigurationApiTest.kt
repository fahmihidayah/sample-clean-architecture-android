package com.widsons.intro.data.remote

import com.widsons.intro.data.utils.MockNetworkModule
import com.widsons.intro.di.ConfigurationNetworkModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class ConfigurationApiTest {

    val mockNetworkModule = MockNetworkModule()

    @Before
    fun setUp() {

    }

    @Test
    fun `test configuration api request success`() = runTest {

        val configurationApi: ConfigurationApi =
            ConfigurationNetworkModule().providesConfigurationApi(
                mockNetworkModule.providesRetrofitSuccess()
            )
        val response = configurationApi.getConfiguration()
        Assert.assertEquals(5, response.details.size)
    }

    @Test
    fun `test configuration api able to serialize data true`() = runTest {
        val configurationApi: ConfigurationApi =
            ConfigurationNetworkModule().providesConfigurationApi(
                mockNetworkModule.providesRetrofitSuccess()
            )
        val response = configurationApi.getConfiguration()
        Assert.assertNotNull(response.details[0].key)
    }

    @Test
    fun `test configuration api return exception true`() {
        val configurationApi: ConfigurationApi =
            ConfigurationNetworkModule().providesConfigurationApi(
                mockNetworkModule.providesRetrofitFailure()
            )
        Assert.assertThrows(IOException::class.java) {
            runBlocking {
                val response = configurationApi.getConfiguration()
                Assert.fail("should throw IOException")
            }
        }
    }

    @Test
    fun `test configuration api failure response true`() {
        val configurationApi: ConfigurationApi =
            ConfigurationNetworkModule().providesConfigurationApi(
                mockNetworkModule.providesRetrofitResponseFailure()
            )
        Assert.assertThrows(HttpException::class.java) {
            runBlocking {
                val response = configurationApi.getConfiguration()
                Assert.fail("should throw HttpException")
            }
        }
    }
}