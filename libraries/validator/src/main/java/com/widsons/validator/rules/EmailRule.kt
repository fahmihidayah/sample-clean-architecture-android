package com.widsons.validator.rules

import androidx.core.util.PatternsCompat
import com.widsons.validator.ValidateField
import com.widsons.validator.data.ValidationResult
import kotlinx.android.parcel.Parcelize

@Parcelize
class EmailRule : Rule {
    override fun validateField(validateField: ValidateField): ValidationResult {
        val field = validateField.fieldValue
        if(field is String && PatternsCompat.EMAIL_ADDRESS.matcher(field).matches()) {
            return ValidationResult().apply { setValidateField(validateField, true) }
        }
        return ValidationResult().apply { setValidateField(validateField, false) }
    }
}