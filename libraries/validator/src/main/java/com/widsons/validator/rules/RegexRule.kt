package com.widsons.validator.rules

import com.widsons.validator.ValidateField
import com.widsons.validator.data.ValidationResult
import kotlinx.parcelize.Parcelize

@Parcelize
class RegexRule(val regex: String) : Rule {

    override fun validateField(validateField: ValidateField): ValidationResult {
        return ValidationResult().apply {
            setValidateField(
                validateField,
                isValid = regex.toRegex().matches(validateField.fieldValue?.toString() ?: "")
            )
        }
    }
}