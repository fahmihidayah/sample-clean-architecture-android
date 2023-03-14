package com.widsons.intro.data.repository

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.widsons.intro.data.remote.ConfigurationApi
import com.widsons.intro.domain.repository.ConfigurationRepository
import com.widsons.intro.utils.JsonProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
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
class ConfigurationRepositoryTest {

    var configurationApi : ConfigurationApi = mock()

    var configurationRepository : ConfigurationRepository = ConfigurationRepositoryImpl(
        configurationApi
    )

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
        given(configurationApi.getConfiguration()).willAnswer { inv -> throw IOException("Io exception") }
    }

    @Test
    fun `test configuration repository request success`() = runTest {
        `prepare success response`()

        configurationRepository.getConfiguration().test {
            Truth.assertThat(awaitItem().details.size).isEqualTo(5)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `test configuration repository request failure true`() = runTest {
        `prepare failure response`()

        configurationRepository.getConfiguration().test {
            Truth.assertThat(awaitItem().error).isEqualTo(true)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `test configuration repository request throw exception true`() = runTest {
        `prepare failure response throw exception`()

        configurationRepository.getConfiguration().test {
            Truth.assertThat(awaitError().message).isEqualTo("Io exception")
            cancelAndIgnoreRemainingEvents()
        }
    }

    @After
    fun tearDown() {

    }
}