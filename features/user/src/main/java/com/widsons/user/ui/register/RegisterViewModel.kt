package com.widsons.user.ui.register

import androidx.lifecycle.viewModelScope
import com.widsons.core.state.UIState
import com.widsons.ui.viewmodel.BaseViewModel
import com.widsons.user.data.model.RegisterForm
import com.widsons.user.data.model.User
import com.widsons.user.domain.usecase.RegisterUseCase
import com.widsons.user.domain.usecase.RegisterUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    val registerUseCase: RegisterUseCaseImpl
) : BaseViewModel() {

    private val _registerStateFlow = MutableStateFlow<UIState<User>>(UIState.Initial())
    val registerStateFlow : StateFlow<UIState<User>> = _registerStateFlow

    var registerForm : RegisterForm = RegisterForm()

    fun register() {
        viewModelScope.launch {
            _registerStateFlow.value = UIState.Loading()
            registerUseCase.invoke(registerForm).catch {
                _registerStateFlow.value = UIState.Error(it.message)
            }.collect{
                _registerStateFlow.value = UIState.Success(it.details)
            }
        }
    }

}