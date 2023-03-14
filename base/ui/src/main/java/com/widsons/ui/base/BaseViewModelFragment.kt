package com.widsons.ui.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModelFragment<VM : ViewModel> : BaseFragment() {

    abstract val viewModel : VM

}