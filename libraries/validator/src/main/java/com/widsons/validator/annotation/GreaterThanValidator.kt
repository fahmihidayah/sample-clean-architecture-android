package com.widsons.validator.annotation

import com.widsons.validator.data.ValidationResult
import com.widsons.validator.utils.getFieldValue
import java.lang.reflect.Field


/**
 * Created on : September/06/2021
 * Author     : Muhammad Fahmi Hidayah
 * Company    : PiXilApps
 * Project    : Yemnak
 */

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class GreaterThan(
    val errorRes: Int = -1,
    val error: String = "",
    val referenceField : String
)

fun GreaterThan.annotationValidate(field : Field, targetObject : Any): ValidationResult? {
    val value = field.getFieldValue(targetObject)
    return when(value) {
        is String -> {
            field.isAccessible = true
            var referenceValue = field.get(targetObject)
            field.isAccessible = false

            if(value.isEmpty()) {
                ValidationResult(errorRes = errorRes, error = error, isValid = false)
            }
            else if(value.toFloat() < (referenceValue?.toString()?.toFloat()?:0.0f)) {
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