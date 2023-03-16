package com.widsons.validator.rules

import com.widsons.validator.ValidateField
import com.widsons.validator.data.ValidationResult
import kotlinx.parcelize.Parcelize

@Parcelize
class CivilIDRule(
    var id : Int = 0,
    val errorMessageId : Int = -1
) : Rule {
    override fun validateField(validateField: ValidateField): ValidationResult {
        val fieldValue = validateField.fieldValue
        if(fieldValue is String && isValidCivilId(fieldValue)) {
            return ValidationResult().apply {
                setValidateField(validateField, true)
                if(errorMessageId != -1) {
                    errorRes = errorMessageId
                }
            }
        }
        return ValidationResult().apply {
            setValidateField(validateField, false)
            if(errorMessageId != -1) {
                errorRes = errorMessageId
            }
        }
    }

    fun isValidCivilId(civilID: String): Boolean {
        return if (civilID.length == 12) {
            val m01 = Character.getNumericValue(civilID[0]) * 2
            val m02 = Character.getNumericValue(civilID[1])
            val m03 = Character.getNumericValue(civilID[2]) * 6
            val m04 = Character.getNumericValue(civilID[3]) * 3
            val m05 = Character.getNumericValue(civilID[4]) * 7
            val m06 = Character.getNumericValue(civilID[5]) * 9
            val m07 = Character.getNumericValue(civilID[6]) * 10
            val m08 = Character.getNumericValue(civilID[7]) * 5
            val m09 = Character.getNumericValue(civilID[8]) * 8
            val m10 = Character.getNumericValue(civilID[9]) * 4
            val m11 = Character.getNumericValue(civilID[10]) * 2
            val m12 = Character.getNumericValue(civilID[11])
            val sum11 = m01 + m02 + m03 + m04 + m05 + m06 + m07 + m08 + m09 + m10 + m11
            val nd12 = 11 - sum11 % 11
            m12 == nd12
        } else {
            false
        }
    }
}