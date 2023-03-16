package com.widsons.user.ui.login

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.widsons.core.state.UIState
import com.widsons.ui.viewmodel.BaseViewModel
import com.widsons.user.data.model.LoginForm
import com.widsons.user.data.model.User
import com.widsons.user.domain.usecase.LoginUseCase
import com.widsons.user.domain.usecase.LoginUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class LoginViewModel
@Inject
constructor(
    val loginUseCase: LoginUseCaseImpl
) : BaseViewModel() {

    private val _loginSateFlow = MutableStateFlow<UIState<User>>(UIState.Initial())
    val loginStateFlow : StateFlow<UIState<User>> = _loginSateFlow

    var loginForm : LoginForm = LoginForm()

    fun login() {
        viewModelScope.launch {
            _loginSateFlow.value = UIState.Loading()
            loginUseCase.invoke(loginForm).catch { e ->
                _loginSateFlow.value = UIState.Error(e.message)
            }.collect {
                _loginSateFlow.value = UIState.Success(it.details)
            }
        }
    }

}