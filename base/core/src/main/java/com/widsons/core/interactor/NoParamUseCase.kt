package com.widsons.core.interactor

interface NoParamUseCase<out Result> {
    suspend operator fun invoke() : Result
}