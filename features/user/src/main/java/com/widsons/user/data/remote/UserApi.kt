package com.widsons.user.data.remote

import com.widsons.core.data.model.BaseResponse
import com.widsons.user.data.model.User
import com.widsons.user.data.model.UserForm
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {
    @POST("login")
    suspend fun login(
        @Body userForm: UserForm
    ): BaseResponse<User>

    @POST("register")
    suspend fun register()

    @GET("profile")
    suspend fun getProfile()
}