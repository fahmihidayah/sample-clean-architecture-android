package com.widsons.user.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.widsons.ui.base.BaseFragment
import com.widsons.ui.base.BaseViewModelFragment
import com.widsons.user.databinding.RegisterFragmentBinding
import com.widsons.validator.utils.validateFormShowResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : BaseViewModelFragment<RegisterViewModel>(), RegisterAction {

    override val viewModel: RegisterViewModel by viewModels()

    override fun onClickRegister() {
        viewModel.registerForm.validateFormShowResult(this) {
            if (it) {
                viewModel.register()
            }
        }
    }

    lateinit var binding : RegisterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegisterFragmentBinding.inflate(inflater)
        setToolbarTitle("Register")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerForm = viewModel.registerForm
        binding.registerAction = this
        lifecycleScope.launch {
            viewModel.registerStateFlow.collect {
                processUiState(it, onFailure =  {

                }, onSuccess = {

                })
            }
        }
    }

    override fun getLoadingView(): View? = binding.loadingLayout.root
}