package com.widsons.validator.annotation

import com.widsons.validator.data.ValidationResult
import com.widsons.validator.utils.getFieldValue
import java.lang.reflect.Field

/**
 * Created on : December/04/2020
 * Author     : Muhammad Fahmi Hidayah
 * Company    : PiXilApps
 * Project    : Yemnak
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class NotEmpty(
    val errorRes: Int = -1,
    val error: String = ""
)

fun NotEmpty.annotationValidate(field : Field, targetObject : Any) : ValidationResult? {
    val value = field.getFieldValue(targetObject)
    return when (value) {
        is String -> {
            if (value.isEmpty()) {
                ValidationResult(errorRes = this.errorRes, error = this.error, isValid = false)
            } else {
                ValidationResult(isValid = true)
            }
        }
        else -> {
            null
        }
    }
}