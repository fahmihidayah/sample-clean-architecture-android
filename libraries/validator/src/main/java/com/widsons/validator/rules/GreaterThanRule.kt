package com.widsons.validator.rules

import com.widsons.validator.ValidateField
import com.widsons.validator.data.ValidationResult
import kotlinx.android.parcel.Parcelize

@Parcelize
class GreaterThanRule : Rule {
    override fun validateField(validateField: ValidateField): ValidationResult {
        return ValidationResult().apply { setValidateField(validateField, true) }
    }
}