package com.widsons.validator.rules

import com.widsons.validator.ValidateField
import com.widsons.validator.data.ValidationResult
import kotlinx.android.parcel.Parcelize

@Parcelize
class NotEmptyRule : Rule {

    override fun validateField(validateField: ValidateField) : ValidationResult {
        val field = validateField.fieldValue
        if(field is String && field.isNotEmpty()) {
            return ValidationResult().apply { setValidateField(validateField, true) }
        }
        return ValidationResult().apply { setValidateField(validateField, false) }
    }

}