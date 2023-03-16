package com.widsons.validator.rules

import android.os.Parcelable
import com.widsons.validator.ValidateField
import com.widsons.validator.data.ValidationResult

interface Rule : Parcelable {
    fun validateField(validateField : ValidateField) : ValidationResult
}