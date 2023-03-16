package com.widsons.validator.annotation

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
annotation class Min(
    val errorRes: Int = -1,
    val error: String = "",
    val min: Int = 5
)

fun Min.annotationValidate(field : Field, targetObject : Any) : ValidationResult? {
    val value = field.getFieldValue(targetObject)
    return when(value) {
        is String -> {
            if(value.length < this.min) {
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