package com.widsons.user.data.repository

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.widsons.core.data.model.BaseResponse
import com.widsons.core.utils.JsonProvider
import com.widsons.user.data.model.User
import com.widsons.user.data.model.LoginForm
import com.widsons.user.domain.repository.UserRepository
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
class UserRepositoryTest {

    val userRepository : UserRepository = mock()

    val userForm : LoginForm = LoginForm(username = "fahmi", password = "123456789")

    @Before
    fun setUp() {

    }

    fun `prepare success response`() = runBlocking {
        given(userRepository.login(userForm)).willReturn {
            flow {
                emit(JsonProvider.resToObj<BaseResponse<User>>("/v1/login/login_success_response.json"))
            }
        }
    }

    fun `prepare failure response`() = runBlocking {
        given(userRepository.login(userForm)).willReturn {
            flow {
                emit(JsonProvider.resToObj<BaseResponse<User>>("/v1/login/failure_response.json"))
            }
        }
    }

    @Test
    fun `test user repository response success true` () = runTest {
        `prepare success response`()
        userRepository.login(userForm).test {
            Truth.assertThat(awaitItem().details.username).isNotNull()
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `test user repository response failure true`() = runTest {
        `prepare failure response`()
        userRepository.login(userForm).test {
            Truth.assertThat(awaitItem().error).isTrue()
            cancelAndIgnoreRemainingEvents()
        }
    }

    @After
    fun tearDown() {

    }

}