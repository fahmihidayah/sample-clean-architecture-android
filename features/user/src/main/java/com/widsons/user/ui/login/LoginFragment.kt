package com.widsons.user.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.widsons.ui.base.BaseFragment
import com.widsons.ui.base.BaseViewModelFragment
import com.widsons.user.R
import com.widsons.user.databinding.LoginFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseViewModelFragment<LoginViewModel>(){
    override val viewModel: LoginViewModel by viewModels()
    lateinit var binding : LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        setToolbarTitle("Login")
        return binding.root
    }


}
