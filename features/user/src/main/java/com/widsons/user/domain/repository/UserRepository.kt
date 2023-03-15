package com.widsons.user.domain.repository

import com.widsons.core.data.model.BaseResponse
import com.widsons.user.data.model.User
import com.widsons.user.data.model.UserForm
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun login(userForm: UserForm) : Flow<BaseResponse<User>>
}