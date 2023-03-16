package com.widsons.user.di

import com.widsons.user.data.remote.UserApi
import com.widsons.user.data.repository.UserRepositoryImpl
import com.widsons.user.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UserRepositoryModule {

    @Provides
    fun provideUserRepository(userApi: UserApi) : UserRepository = UserRepositoryImpl(userApi)
}