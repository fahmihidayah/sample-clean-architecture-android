package com.widsons.validator.rules

import com.widsons.validator.ValidateField
import com.widsons.validator.data.ValidationResult
import kotlinx.parcelize.Parcelize

@Parcelize
class NotNullRule : Rule {

    override fun validateField(validateField: ValidateField) : ValidationResult {
        val field = validateField.fieldValue
        if(field != null) {
            return ValidationResult().apply { setValidateField(validateField, true) }
        }
        return ValidationResult().apply { setValidateField(validateField, false) }
    }

}