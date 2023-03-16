package com.widsons.ui.base

import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    open fun getBaseActivity() = (requireActivity()) as BaseActivity

    open fun showError(message : String? = "") {
        getErrorView()?.visibility = View.VISIBLE
        getErrorText()?.text = message
        getContentView()?.visibility = View.GONE

    }

    open fun hideError() {
        getErrorView()?.visibility = View.GONE
        getContentView()?.visibility = View.VISIBLE
    }

    open fun getErrorView() : View? = null

    open fun getErrorText() : TextView? = null

    open fun getContentView() : View? = null

    open fun getLoadingView() : View? = null

    fun setToolbarTitle(title : String) {
        getBaseActivity().supportActionBar?.title = title
    }

    fun setToolbarTitle(@StringRes title : Int ) {
        getBaseActivity().supportActionBar?.setTitle(title)
    }





}