package com.widsons.user.data.remote

import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {
    @POST("login")
    suspend fun login()

    @POST("register")
    suspend fun register()

    @GET("profile")
    suspend fun getProfile()
}