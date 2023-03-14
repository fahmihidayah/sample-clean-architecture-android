package com.widsons.intro.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.widsons.core.state.UIState
import com.widsons.intro.domain.usecase.LoadConfigurationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    val loadConfigurationUseCase: LoadConfigurationUseCase
) : ViewModel() {

    private val _stateUiFlow = MutableStateFlow<UIState<String?>>(UIState.Loading())
    val stateUiFlow : StateFlow<UIState<String?>> = _stateUiFlow

    fun loadConfiguration() {
        viewModelScope.launch {
            loadConfigurationUseCase
                .invoke()
                .catch { e ->
                _stateUiFlow.value = UIState.Error(e.message)
            }.collect {
                _stateUiFlow.value = UIState.Success("Success")
            }
        }
    }

}