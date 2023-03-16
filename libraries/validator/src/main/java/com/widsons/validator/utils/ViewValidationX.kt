package com.widsons.validator.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import com.widsons.validator.FormValidatable
import com.widsons.validator.ValidationListenerHolder
import com.widsons.validator.custom.ITextInputLayout
import com.widsons.validator.custom.ITextView
import com.widsons.validator.custom.IViewValidate
import com.widsons.validator.data.ValidationResult

/**
 * Created on : June/24/2021
 * Author     : Muhammad Fahmi Hidayah
 * Company    : PiXilApps
 * Project    : TodoApps
 */

fun List<ValidationResult>.filterVisibleValidationResult(view: ViewGroup): List<ValidationResult> {
    return this.filter { if (it.targetId != -1) view.findViewById<View>(it.targetId)?.visibility == View.VISIBLE else true }
}


fun FormValidatable.initialEditFocusValidationListener(fragment: Fragment) {
    (fragment.view as ViewGroup?)?.let {
        initialEditFocusValidationListener(it)
    }
}

fun FormValidatable.initialValidationListener(view: ViewGroup) : ValidationListenerHolder {
    initialEditFocusValidationListener(view)
    val validationListenerHolder = ValidationListenerHolder()
    getValidateFields().forEach { validateField ->
        if(validateField.viewId != -1) {
            view.findViewById<View>(validateField.viewId)?.let {
                if(it is TextView) {
                    val textWatcher = object : TextWatcher {
                        override fun beforeTextChanged(
                            p0: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {

                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                        }

                        override fun afterTextChanged(p0: Editable?) {
                            if(!it.hasFocus())
                                onFocusChangeListener(view, false, validateField.fieldName)
                        }
                    }
                    with(it) {
                        validationListenerHolder.put(it, textWatcher)

                        this.addTextChangedListener(textWatcher)
                    }

                }
                else if(it is TextInputLayout) {
                    val textWatcher = object : TextWatcher {
                        override fun beforeTextChanged(
                            p0: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {

                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                        }

                        override fun afterTextChanged(p0: Editable?) {
                            if(it.editText?.hasFocus() == false)
                                onFocusChangeListener(view, false, validateField.fieldName)
                        }
                    }

                    it.editText?.let {
                        validationListenerHolder.put(it, textWatcher)
                        it.addTextChangedListener(textWatcher)
                    }

                }
                else if(it is ITextInputLayout && it.getChildTextInputLayout() != null) {
                    val textWatcher = object : TextWatcher {
                        override fun beforeTextChanged(
                            p0: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {

                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                        }

                        override fun afterTextChanged(p0: Editable?) {
                            if(it.getChildTextInputLayout()?.editText?.hasFocus() == false)
                                onFocusChangeListener(view, false, validateField.fieldName)
                        }
                    }

                    it.getChildTextInputLayout()?.editText?.let {
                        validationListenerHolder.put(it, textWatcher)
                        it.addTextChangedListener(textWatcher)
                    }
                }
                else if(it is ITextView && it.getChildTextView() != null) {
                    val textWatcher = object : TextWatcher {
                        override fun beforeTextChanged(
                            p0: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {

                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                        }

                        override fun afterTextChanged(p0: Editable?) {
                            if(it.getChildTextView()?.hasFocus() == false)
                                onFocusChangeListener(view, false, validateField.fieldName)
                        }
                    }

                    it.getChildTextView()?.let {
                        validationListenerHolder.put(it, textWatcher)
                        it.addTextChangedListener(textWatcher)
                    }
                }
                else {
                    null
                }
            }
        }
    }
    return validationListenerHolder
}

fun FormValidatable.initialEditFocusValidationListener(view: ViewGroup) {
    getValidateFields().forEach { validateField ->
        if(validateField.viewId != -1) {
            view.findViewById<View>(validateField.viewId)?.let {
                if(it is TextView) {

                    it.onFocusChangeListener = object : View.OnFocusChangeListener {
                        override fun onFocusChange(p0: View?, p1: Boolean) {
                            this@initialEditFocusValidationListener.onFocusChangeListener(view, p1, validateField.fieldName)
                        }
                    }
                }
                else if(it is TextInputLayout) {
                    it.editText?.onFocusChangeListener = object : View.OnFocusChangeListener {
                        override fun onFocusChange(p0: View?, p1: Boolean) {
                            this@initialEditFocusValidationListener.onFocusChangeListener(view, p1, validateField.fieldName)
                        }
                    }
                }
                else if(it is ITextInputLayout) {
                    it.getChildTextInputLayout()?.editText?.onFocusChangeListener = object : View.OnFocusChangeListener {
                        override fun onFocusChange(p0: View?, p1: Boolean) {
                            this@initialEditFocusValidationListener.onFocusChangeListener(view, p1, validateField.fieldName)
                        }
                    }
                }
                else if(it is ITextView) {
                    it.getChildTextView()?.onFocusChangeListener = object : View.OnFocusChangeListener {
                        override fun onFocusChange(p0: View?, p1: Boolean) {
                            this@initialEditFocusValidationListener.onFocusChangeListener(view, p1, validateField.fieldName)
                        }
                    }
                }
            }
        }
    }
}

fun FormValidatable.onFocusChangeListener(fragment: Fragment, isFocus : Boolean, fieldName: String) {
    onFocusChangeListener(fragment.requireView() as ViewGroup, isFocus = isFocus, fieldName)
}


fun FormValidatable.onFocusChangeListener(view: ViewGroup, isFocus : Boolean, fieldName: String) {
    if(isFocus) {
        this.clearError(view, fieldName)
    }
    else {
        fillFormValue().find { it.fieldName == fieldName }?.validateField()?.showResult(view)
    }
}

fun FormValidatable.validateFormShowResult(
    fragment: Fragment,
    onResultValidationListener: (Boolean) -> Unit = {}
) {
    val fragmentView = fragment.view
    if (fragmentView != null) {
        validateForm().showResult(fragmentView as ViewGroup, onResultValidationListener)
    }
}

@Deprecated("not compatible with android")
fun Any.validateObjectAndShowResult(
    fragment: Fragment,
    onResultValidationListener: (Boolean) -> Unit = {}
) {
    val fragmentView = fragment.view
    if (fragmentView != null) {
        validate().showResult(fragmentView as ViewGroup, onResultValidationListener)
    }
}

//fun Any.clearError(fragment: Fragment, fieldName: String? = null) {
//    (fragment.view as ViewGroup?)?.let {
//        this.clearError(it, fieldName)
//    }
//}

fun FormValidatable.clearError(viewGroup : ViewGroup, fieldName : String) {
    getValidateFields().find { it.fieldName == fieldName }?.getCorrectValidationResult()?.showResult(viewGroup)
}

fun Any.validateObjectAndShowResult(
    view: ViewGroup,
    onResultValidationListener: (Boolean) -> Unit = {}
) {
    validate().showResult(view, onResultValidationListener)
}

fun List<ValidationResult>.showResult(
    view: ViewGroup,
    onResultValidationListener: (Boolean) -> Unit = {}
) {
    this.filterVisibleValidationResult(view).let {
        showValidationResultToView(it, view)
        onResultValidationListener(it.isValid())
    }
}
fun List<ValidationResult>.showResult(
    fragment: Fragment,
    onResultValidationListener: (Boolean) -> Unit = {}
) {
    (fragment.view as ViewGroup)?.let {
        this.filterVisibleValidationResult(it).let { itValidationResults ->
            showValidationResultToView(itValidationResults, it)
            onResultValidationListener(itValidationResults.isValid())
        }
    }

}

fun showValidationResultToView(validationResults: List<ValidationResult>, view: ViewGroup) {
    validationResults.forEachIndexed { index, validationResult ->

        val targetViewId = validationResult.errorViewId
        if (targetViewId == -1) {
            if (validationResult.getMessage(view.context).isNotEmpty())
                Toast.makeText(
                    view.context,
                    validationResult.getMessage(view.context),
                    Toast.LENGTH_SHORT
                ).show()
        } else {
            when (val viewTarget = view.findViewById<View>(validationResult.errorViewId)) {
                is IViewValidate -> {
                    viewTarget.onSetErrorMessage(validationResult.getMessage(view.context))
                    if (validationResult.isValid) {
                        viewTarget.onHideError()
                    } else
                        viewTarget.onShowError()
                }
                is TextInputLayout -> {
                    if (validationResult.isValid) {
                        viewTarget.isErrorEnabled = false
                        viewTarget.error = ""
                    } else {
                        viewTarget.isErrorEnabled = true
                        viewTarget.error = validationResult.getMessage(view.context)
                    }
                }
                is TextView -> {
                    if (validationResult.isValid) {
                        viewTarget.visibility = View.GONE
                    } else {
                        viewTarget.visibility = View.VISIBLE
                        viewTarget.text = validationResult.getMessage(view.context)
                    }
                }
                else -> {
                    if (validationResult.isValid) {
                        viewTarget?.visibility = View.GONE
                    } else {
                        viewTarget?.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}
