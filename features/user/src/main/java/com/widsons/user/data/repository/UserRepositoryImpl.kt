package com.widsons.user.data.repository

import com.widsons.user.data.model.LoginForm
import com.widsons.user.data.remote.UserApi
import com.widsons.user.domain.repository.UserRepository
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(
    val userApi: UserApi
) : UserRepository {
    override suspend fun login(userForm: LoginForm) = flow {
        emit(userApi.login(userForm))
    }
}