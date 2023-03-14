package com.widsons.core.interactor

interface BaseUseCase<in Param, out Result> {
    suspend operator fun invoke(params : Param) : Result
}