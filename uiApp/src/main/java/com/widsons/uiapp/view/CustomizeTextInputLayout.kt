package com.widsons.uiapp.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.children
import com.google.android.material.textfield.TextInputLayout
import com.widsons.ui.utils.*
import com.widsons.uiapp.R
import com.widsons.validator.custom.ITextInputLayout
import com.widsons.validator.custom.ITextView
import com.widsons.validator.custom.IViewValidate

class CustomizeTextInputLayout(context: Context?, attrs: AttributeSet?) :
    LinearLayout(context, attrs),
    IViewValidate,
    ITextInputLayout,
    ITextView {
    override fun getChildTextView(): TextView? {
        return textViewContent
    }

    override fun getChildTextInputLayout(): TextInputLayout? {
        return textInputLayout
    }

    override fun onSetErrorMessage(message: String) {
        errorTextView.text = message
    }

    override fun onShowError() {
        textInputLayout?.setBackgroundResource(R.drawable.edit_text_error_background)
        textViewContent?.setBackgroundResource(R.drawable.edit_text_error_background)
        errorTextView.visibility = View.VISIBLE
    }

    override fun onHideError() {
        textInputLayout?.setBackgroundResource(R.drawable.edit_text_background)
        textViewContent?.setBackgroundResource(R.drawable.edit_text_background)
        errorTextView.visibility = View.GONE
    }

    var textInputLayout : TextInputLayout? = null

    var textViewContent : TextView? = null

    val titleTextView: TextView = TextView(context).apply {
        text = "Title"
        setTextSizeResource(com.widsons.ui.R.dimen.text_medium)
    }
    val errorTextView: TextView = TextView(context).apply {
        text = "Error"
        setTextColor(Color.RED)
        setTextSizeResource(com.widsons.ui.R.dimen.text_medium)
    }

    init {
        orientation = LinearLayout.VERTICAL
        addView(titleTextView)
        addView(errorTextView)
        errorTextView.visibility = View.GONE
        initial(context, attrs)
    }

    private fun initial(context: Context?, attrs: AttributeSet?) {
        getTypedArray(context, attrs, R.styleable.CustomizeTextInputLayout) {
            R.styleable.CustomizeTextInputLayout_ctl_error.setString(it, errorTextView)
            R.styleable.CustomizeTextInputLayout_ctl_label.setString(it, titleTextView)

            R.styleable.CustomizeTextInputLayout_ctl_error_color.setTextColor(it, errorTextView)
            R.styleable.CustomizeTextInputLayout_ctl_label_color.setTextColor(it, titleTextView)

            R.styleable.CustomizeTextInputLayout_ctl_error_size.setTextSize(it, errorTextView)
            R.styleable.CustomizeTextInputLayout_ctl_label_size.setTextSize(it, titleTextView)

        }
    }

    override fun addView(child: View?) {
        if(child != titleTextView && child != errorTextView) {
            removeView(errorTextView)
            textInputLayout = findTextInputLayout(child)
            textViewContent = findTextViewContent(child)
            println("is ${titleTextView.text} text input layout null ${textInputLayout}")
            super.addView(child)
            addView(errorTextView)
        }
        else {
            super.addView(child)
        }
    }

    override fun addView(child: View?, params: ViewGroup.LayoutParams?) {
        if(child != titleTextView && child != errorTextView) {
            removeView(errorTextView)
            textInputLayout = findTextInputLayout(child)
            textViewContent = findTextViewContent(child)
            println("is ${titleTextView.text} text input layout null ${textInputLayout}")
            super.addView(child, params)
            addView(errorTextView, errorTextView.layoutParams)
        }
        else {
            super.addView(child, params)
        }
    }

    private fun findTextViewContent(child: View?) : TextView? {
        if(child is TextView) {
            return child
        }
        else return null
    }

    private fun findTextInputLayout(child : View?) : TextInputLayout? {
        if(child is TextInputLayout) {
            return child
        }
        else if(child is ViewGroup) {
            var result : TextInputLayout? = null
            child.children.iterator().forEach { it ->
                if(it is TextInputLayout) {
                    result = it
                }
            }
            return result
        }
        else {
            return null
        }
    }

}

fun ViewGroup.LayoutParams.isWeightIsOne() : Boolean {
    if(this is LinearLayout.LayoutParams) {
        return this.weight == 1f
    }
    else
        return false
}

fun View.isLayoutParamWeightIsOne() : Boolean {
    if(this.layoutParams == null) return false
    return this.layoutParams.isWeightIsOne()
}