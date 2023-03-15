package com.widsons.user.data.remote

import com.widsons.core.data.model.BaseResponse
import com.widsons.core.utils.JsonProvider
import com.widsons.user.data.model.User
import com.widsons.user.data.model.LoginForm
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.willReturn

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserApiTest {

    val userApi : UserApi = mock()

    val userForm : LoginForm = LoginForm(
        username = "fahmi",
        password = "123456789"
    )

    @Before
    fun setUp() {

    }

    fun `prepare success response`() = runBlocking {
        given(userApi.login(userForm)).willReturn {
            JsonProvider.resToObj<BaseResponse<User>>("/v1/login/login_success_response.json")
        }
    }

    fun `prepare failure response`() = runBlocking {
        given(userApi.login(userForm)).willReturn {
            JsonProvider.resToObj<BaseResponse<User>>("/v1/login/failure_response.json")
        }
    }

    @Test
    fun `test user api login success true`() = runTest {
        `prepare success response`()
        val response = userApi.login(userForm)
        Assert.assertNotNull(response.details.email)
    }

    @Test
    fun `test user api login failure true`() = runTest {
        `prepare failure response`()
        val response = userApi.login(userForm)
        Assert.assertTrue(response.error)
    }

    @After
    fun tearDown() {

    }

}