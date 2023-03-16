package com.widsons.user.domain.usecase

import com.widsons.core.data.model.BaseResponse
import com.widsons.core.interactor.BaseUseCase
import com.widsons.user.data.model.RegisterForm
import com.widsons.user.data.model.User
import com.widsons.user.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

typealias RegisterUseCase = BaseUseCase<RegisterForm, Flow<BaseResponse<User>>>

@Singleton
class RegisterUseCaseImpl @Inject constructor(
    val userRepository: UserRepository
) : RegisterUseCase {
    override suspend fun invoke(params: RegisterForm) = userRepository.register(params)
}