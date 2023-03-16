package com.widsons.user.data.remote

import com.widsons.core.data.model.BaseResponse
import com.widsons.user.data.model.User
import com.widsons.user.data.model.LoginForm
import com.widsons.user.data.model.RegisterForm
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {
    @POST("login")
    suspend fun login(
        @Body userForm: LoginForm
    ): BaseResponse<User>

    @POST("register")
    suspend fun register(
        @Body registerForm: RegisterForm
    ) : BaseResponse<User>

    @GET("profile")
    suspend fun getProfile()
}