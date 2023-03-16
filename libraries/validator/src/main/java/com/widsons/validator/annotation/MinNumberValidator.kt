package com.widsons.validator.annotation

import com.widsons.validator.data.ValidationResult
import com.widsons.validator.data.ValidationResultWithExtraMessage
import com.widsons.validator.utils.getFieldValue
import java.lang.reflect.Field

/**
 * Created on : November/10/2021
 * Author     : Muhammad Fahmi Hidayah
 * Company    : PiXilApps
 * Project    : ModularApp
 */

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class MinNumber(val errorRes : Int = -1,
                           val error : String = "",
                           val min : Int = 5,
                           val referenceField : String = "")


fun MinNumber.annotationValidate(field : Field, targetObject : Any) : ValidationResult? {
    val value = field.getFieldValue(targetObject)
    return when(value) {
        is String -> {
            var field = targetObject::class.java.declaredFields.find { it ->
                it.name == referenceField
            }
            var referenceValue =  if(field != null) {
                field.isAccessible = true
                var valueField = field?.get(targetObject)
                field.isAccessible = false
                valueField
            }
            else {
                ""
            }
            if(value.isEmpty() || referenceValue.toString().isEmpty()) {
                ValidationResultWithExtraMessage(errorRes = errorRes,
                    error = error,
                    isValid = false,
                    extraMessage = referenceValue.toString())
            }
            else if(referenceValue.toString().isNotEmpty()) {
                if(value.toFloat() < referenceValue.toString().toFloat()) {
                    ValidationResultWithExtraMessage(errorRes = errorRes,
                        error = error,
                        isValid = false,
                        extraMessage = referenceValue.toString())
                } else {
                    ValidationResult(isValid = true)
                }
            }
            else {
                if(value.toFloat() < min) {
                    ValidationResultWithExtraMessage(errorRes = errorRes,
                        error = error,
                        isValid = false,
                        extraMessage = referenceValue.toString())
                } else {
                    ValidationResult(isValid = true)
                }
            }


        }
        else -> {
            null
        }
    }
}