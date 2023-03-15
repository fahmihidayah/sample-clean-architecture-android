package com.widsons.user.di

import com.widsons.user.data.remote.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class UserNetworkModule {

    @Provides
    fun provideUserApi(retrofit: Retrofit) : UserApi = retrofit.create(UserApi::class.java)

}