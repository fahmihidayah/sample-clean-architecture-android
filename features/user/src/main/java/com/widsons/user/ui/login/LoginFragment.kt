package com.widsons.user.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.widsons.core.state.UIState
import com.widsons.ui.base.BaseFragment
import com.widsons.ui.base.BaseViewModelFragment
import com.widsons.ui.utils.toast
import com.widsons.user.R
import com.widsons.user.databinding.LoginFragmentBinding
import com.widsons.validator.ValidationListenerHolder
import com.widsons.validator.utils.initialValidationListener
import com.widsons.validator.utils.validateFormShowResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseViewModelFragment<LoginViewModel>(), LoginAction{
    override val viewModel: LoginViewModel by viewModels()
    lateinit var binding : LoginFragmentBinding
    var validationListener : ValidationListenerHolder? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater)
        setToolbarTitle("Login")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginForm = viewModel.loginForm
        binding.loginAction = this
        lifecycleScope.launch {
            viewModel.loginStateFlow.collect {
                processUiState(it, onFailure = {
                    toast(it.error?:"")
                }, onSuccess = {
                    toast("user is ${it.data?.email}")
                })
            }
        }
        validationListener = viewModel.loginForm.initialValidationListener(binding.root as ViewGroup)
    }

    override fun onDestroy() {
        super.onDestroy()
        validationListener?.onDestroy()
    }

    override fun getLoadingView(): View? = binding.loadingLayout.root

    override fun onClickLogin() {
        viewModel.loginForm.validateFormShowResult(this) {
            if(it) {
                viewModel.login()
            }
        }
    }

    override fun onClickRegister() {
        findNavController().navigate(
            R.id.action_loginFragment_to_registerFragment
        )
    }

}
