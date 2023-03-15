package com.widsons.user.data.repository

import com.widsons.core.data.model.BaseResponse
import com.widsons.user.data.model.User
import com.widsons.user.data.model.UserForm
import com.widsons.user.data.remote.UserApi
import com.widsons.user.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(
    val userApi: UserApi
) : UserRepository {
    override suspend fun login(userForm: UserForm) = flow {
        emit(userApi.login(userForm))
    }
}