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
annotation class Equal(
    val errorRes: Int = -1,
    val error: String = "",
    val referenceField : String = ""
)

fun Equal.annotationValidate(field : Field, targetObject : Any) : ValidationResult? {
    val value = field.getFieldValue(targetObject)
    return when(value) {
        is String -> {
            var field = targetObject::class.java.declaredFields.find { it ->
                it.name == this.referenceField
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
            if(value.isEmpty()) {
                ValidationResult(errorRes = this.errorRes, error = this.error, isValid = false)
            }
            else if(value != referenceValue) {
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