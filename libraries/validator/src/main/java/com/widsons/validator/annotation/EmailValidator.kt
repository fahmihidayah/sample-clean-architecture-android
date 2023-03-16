package com.widsons.validator.annotation

import androidx.core.util.PatternsCompat
import com.widsons.validator.data.ValidationResult
import com.widsons.validator.utils.getFieldValue
import java.lang.reflect.Field

/**
 * Created on : December/07/2020
 * Author     : Muhammad Fahmi Hidayah
 * Company    : PiXilApps
 * Project    : Yemnak
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class Email(
    val errorRes: Int = -1,
    val error: String = ""
)

fun Email.annotationValidate(field : Field, targetObject : Any) : ValidationResult? {
    val value = field.getFieldValue(targetObject)
    return when(value) {
        is String -> {
            if(!PatternsCompat.EMAIL_ADDRESS.matcher(value).matches()) {
                ValidationResult(errorRes = errorRes, error = error, isValid = false)
            } else {
                ValidationResult(isValid = true)
            }
        }
        else -> {
            null
        }
    }
}