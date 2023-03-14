package com.widsons.intro.domain.usecase

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.widsons.core.data.local.BuildManager
import com.widsons.intro.data.remote.ConfigurationApi
import com.widsons.intro.data.repository.ConfigurationRepositoryImpl
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
class LoadConfigurationUseCaseTest {

    var configurationApi : ConfigurationApi = mock()

    var configurationRepository : ConfigurationRepository = ConfigurationRepositoryImpl(
        configurationApi
    )

    var buildManager : BuildManager = object  : BuildManager() {
        override val buildConfig: String
            get() = "1.0"
    }

    var loadConfigurationUseCase = LoadConfigurationUseCase(configurationRepository, buildManager)

    @Before
    fun setUp() {

    }

    fun `prepare success response`() = runTest {
        given(configurationApi.getConfiguration())
            .willReturn {
                JsonProvider.reourceToObject("/v1/success_fine_response.json")
            }
    }

    fun `prepare success android_maintenance response`() = runTest {
        given(configurationApi.getConfiguration())
            .willReturn {
                JsonProvider.reourceToObject("/v1/success_android_is_maintenance_response.json")
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
    fun `test load configuration use case return success true`() = runTest {
        `prepare success response`()
        loadConfigurationUseCase.invoke().test {
            Truth.assertThat(awaitItem().size).isEqualTo(5)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `test load configuration use case return success android_is_maintenance true`() = runTest {
        `prepare success android_maintenance response`()
        loadConfigurationUseCase.invoke().test {
            Truth.assertThat(awaitError().message)
                .isEqualTo("Currently android version still under maintenance, we will info you soon when it's available.")
            cancelAndIgnoreRemainingEvents()
        }
    }



    @Test
    fun `test load configuration use case return failure true`() = runTest {
        `prepare failure response`()
        loadConfigurationUseCase.invoke().test {
            Truth.assertThat(awaitError().message).isEqualTo("Failure Request")
            cancelAndIgnoreRemainingEvents()
        }
    }

    @After
    fun tearDown() {

    }
}