package com.widsons.validator

interface FormValidatable {
    fun getValidateFields() : List<ValidateField>
}