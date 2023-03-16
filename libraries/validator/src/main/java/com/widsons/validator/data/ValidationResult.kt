package com.widsons.validator.data

import android.content.Context
import com.widsons.validator.ValidateField


open class ValidationResult(
    var fieldName : String = "",
    var errorRes : Int = -1,
    var error : String = "",
    var targetId : Int = -1,
    var errorViewId : Int = -1,
    var isValid : Boolean = false
) {
    open fun getMessage(context : Context?) : String{
        if(errorRes == -1) {
            return error
        }
        else {
            return context?.getString(errorRes)?:"Error message"
        }
    }

    fun setValidateField(validateField: ValidateField, isValid : Boolean) {
        fieldName = validateField.fieldName
        errorRes = validateField.errorRes
        error = validateField.error
        targetId = validateField.viewId
        errorViewId = if(validateField.errorId == -1) validateField.viewId else validateField.errorId
        this.isValid = isValid
    }
}