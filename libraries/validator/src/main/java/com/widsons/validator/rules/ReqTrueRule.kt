package com.widsons.validator.rules

import com.widsons.validator.ValidateField
import com.widsons.validator.data.ValidationResult
import kotlinx.android.parcel.Parcelize

@Parcelize
class ReqTrueRule : Rule {
    override fun validateField(validateField: ValidateField): ValidationResult {
        val field = validateField.fieldValue
        if(field is Boolean) {
            return ValidationResult().apply { setValidateField(validateField, field) }
        }
        return ValidationResult().apply { setValidateField(validateField, false) }
    }
}