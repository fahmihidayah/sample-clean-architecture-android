package com.widsons.validator.utils

import com.widsons.validator.annotation.*
import com.widsons.validator.data.ValidationResult
import com.widsons.validator.FormValidatable
import com.widsons.validator.ValidateField
import java.lang.reflect.Field

/**
 * Created on : June/21/2021
 * Author     : Muhammad Fahmi Hidayah
 * Company    : PiXilApps
 * Project    : TodoApps
 */

fun Field.getTargetView() : TargetView? {
    return this.annotations.find { it is TargetView } as TargetView?
}

fun Field.getErrorTargetView() : ErrorTargetView? {
    return this.annotations.find { it is ErrorTargetView } as ErrorTargetView?
}

fun TargetView?.getId() : Int {
    return this?.targetId?:-1
}

fun ErrorTargetView?.getId() : Int {
    return this?.errorTargetId?:-1
}

@Deprecated("not compatible with android")
fun Any.findAnnotation(match : (Annotation) -> Boolean) : Pair<Field, Annotation>? {
    this::class.java.declaredFields.forEachIndexed { index, field ->
        field.annotations.forEachIndexed { IndexAnnotation, annotation ->
            if(match(annotation)) {
                field.isAccessible = true
                return field to annotation
            }
        }
    }
    return null
}

fun  Field.getFieldValue(targetObject : Any) : Any? {
    this.isAccessible = true
    return this.get(targetObject)
}

@Deprecated("not compatible with android")
fun Any.getField(fieldName : String) : Field? {
    this::class.java.declaredFields.forEachIndexed {index, field ->
        println("field is ${field.name} - ${fieldName}")
        if(field.name == fieldName) {
            return field
        }
    }
    return null
}

@Deprecated("not compatible with android")
fun Any.getValidateResultFromFieldWithAnnotation(field: Field, annotation : Annotation) : ValidationResult? {
    return when(annotation) {
        is Email -> annotation.annotationValidate(field, this)
        is Equal -> annotation.annotationValidate(field, this)
        is Min -> annotation.annotationValidate(field, this)
        is NotEmpty  -> annotation.annotationValidate(field, this)
        is RequiredChecked -> annotation.annotationValidate(field, this)
        else -> null
    }
}

@Deprecated("not compatible with new android")
fun Field.validateField(targetObject : Any) :  MutableList<ValidationResult>{
    val listResult = mutableListOf<ValidationResult>()
    val targetView = this.getTargetView()
    val errorTargetView = this.getErrorTargetView()
    this.annotations.forEachIndexed { IndexAnnotation, annotation ->
        val result = targetObject.getValidateResultFromFieldWithAnnotation(this, annotation)
        targetView?.targetId?.let {  result?.targetId = it }
        errorTargetView?.errorTargetId?.let { result?.errorViewId = it}
        result?.let { listResult.add(it) }
    }
    return listResult
}

fun ValidateField.getCorrectValidationResult(): List<ValidationResult> =
    rules.map { it.validateField(this).apply { isValid = true } }

@Deprecated("not compatible with android")
fun Field.getCorrectValidationResult() : MutableList<ValidationResult> {
    val listResult = mutableListOf<ValidationResult>()
    val targetView = this.getTargetView()
    val errorTargetView = this.getErrorTargetView()
    this.annotations.forEachIndexed { IndexAnnotation, annotation ->
        val result = ValidationResult(errorRes = -1, error = "", isValid = true)
        targetView?.targetId?.let {  result?.targetId = it }
        errorTargetView?.errorTargetId?.let { result?.errorViewId = it}
        result.let { listResult.add(it) }
    }
    return listResult
}

fun Any.validateWithCorrectResult() : MutableList<ValidationResult> {
    var listResult = mutableListOf<ValidationResult>()
    this::class.java.declaredFields.forEachIndexed { index, field ->
        listResult.addAll(field.getCorrectValidationResult())
    }
    return listResult
}

@Deprecated("not compatible with new android")
fun Any.validate() : MutableList<ValidationResult> {
    var listResult = mutableListOf<ValidationResult>()
    this::class.java.declaredFields.forEachIndexed { index, field ->
        with(field.validateField(this)) {
            if(this.isNotEmpty())
                listResult.addAll(this)
        }
    }
    return listResult
}

fun ValidateField.validateField() = rules.map { it.validateField(this) }

fun FormValidatable.fillFormValue() = getValidateFields().map { validateField ->
    this::class.java.declaredFields.forEachIndexed { index, field ->
        if(validateField.fieldName == field.name) {
            validateField.fieldValue = field.getFieldValue(this)
        }
    }
    validateField
}

fun FormValidatable.validateForm() : MutableList<ValidationResult> {
    val listResult = mutableListOf<ValidationResult>()
    this.fillFormValue().forEachIndexed{ index, validateField ->
        with(validateField.validateField()) {
            if(this.isNotEmpty())
                listResult.addAll(this)
        }
    }

    return listResult.filterUnique()
//    return listResult.distinctBy {
//        it.fieldName
//    }.toMutableList()
}

fun List<ValidationResult>.filterUnique() : MutableList<ValidationResult>{
    val resultFiltered = mutableListOf<ValidationResult>()
    this.forEach {
        if(!it.isValid) {
            if(!resultFiltered.contains(it)) {
                resultFiltered.add(it)
            }
        }
    }

    this.forEach {
        if(!resultFiltered.contains(it)) {
            resultFiltered.add(it)
        }
    }
    return resultFiltered
}

fun List<ValidationResult>.isValid() : Boolean {
    for(item in this) {
        if(!item.isValid)
            return false
    }
    return true
}

@Deprecated("not compatible with android")
fun Any.getFields() = this::class.java.declaredFields
