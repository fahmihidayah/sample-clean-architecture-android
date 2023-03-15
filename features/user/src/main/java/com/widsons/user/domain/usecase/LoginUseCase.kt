package com.widsons.user.domain.usecase

import com.widsons.core.data.model.BaseResponse
import com.widsons.core.interactor.BaseUseCase
import com.widsons.user.data.model.User
import com.widsons.user.data.model.LoginForm
import com.widsons.user.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

typealias LoginUseCase = BaseUseCase<LoginForm, Flow<BaseResponse<User>>>

@Singleton
class LoginUseCaseImpl @Inject constructor(
    val userRepository: UserRepository
) : LoginUseCase {
    override suspend fun invoke(params: LoginForm) = userRepository.login(params)
}