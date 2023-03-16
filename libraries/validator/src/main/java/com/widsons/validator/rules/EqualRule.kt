package com.widsons.validator.rules

import com.widsons.validator.ValidateField
import com.widsons.validator.data.ValidationResult
import kotlinx.android.parcel.Parcelize

@Parcelize
class EqualRule(val other: ValidateField) : Rule {
    override fun validateField(validateField: ValidateField): ValidationResult {
        val field = validateField.fieldValue
        println("field ${field} ${other.fieldValue}")
        if(field is String && field.isEmpty()) {
            return ValidationResult().apply { setValidateField(validateField, false) }
        }
        if(field is String && other.fieldValue is String && (field == other.fieldValue)) {
            return ValidationResult().apply { setValidateField(validateField, true) }
        }
        return ValidationResult().apply { setValidateField(validateField, false) }
    }
}