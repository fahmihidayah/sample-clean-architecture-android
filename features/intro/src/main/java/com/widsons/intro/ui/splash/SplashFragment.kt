package com.widsons.intro.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.widsons.core.state.UIState
import com.widsons.intro.R
import com.widsons.intro.databinding.SplashFragmentBinding
import com.widsons.ui.base.BaseViewModelFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseViewModelFragment<SplashViewModel>() {

    override val viewModel: SplashViewModel by viewModels()
    lateinit var binding : SplashFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.splash_fragment, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBaseActivity().hideToolBar()
        getBaseActivity().hideBottomNavigation()
        lifecycleScope.launchWhenStarted {
            viewModel.stateUiFlow.collect {
                when (it) {
                    is UIState.Error -> {
                        binding.textViewSplash.setText(it.error)
                    }
                    is UIState.Loading -> {

                    }
                    is UIState.Success -> {
                        binding.textViewSplash.setText("Application ready")
                    }
                    else -> {

                    }
                }
            }
        }

        viewModel.loadConfiguration()
    }
}