package com.widsons.ui.base

import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    open fun getBaseActivity() = (requireActivity()) as BaseActivity

}