package com.widsons.validator.annotation

import com.widsons.validator.sampleObject.SignUpForm
import com.widsons.validator.utils.findAnnotation
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created on : June/21/2021
 * Author     : Muhammad Fahmi Hidayah
 * Company    : PiXilApps
 * Project    : TodoApps
 */
@RunWith(MockitoJUnitRunner::class)
class RequiredCheckedValidatorTest {
    var signUpForm  = SignUpForm(
        email = "",
        name = "fahmi",
        password = "",
        confirmPassword = ""
    )

    @Test
    fun `requiredCheckAnnotation validate true`() {
        signUpForm.isAgree = true
        signUpForm.findAnnotation { annotation -> annotation is RequiredChecked}?.let {
            val validationResult = (it.second as RequiredChecked).annotationValidate(it.first, signUpForm)
            assertTrue(validationResult?.isValid?:false)
        }
    }


    @Test
    fun `requiredCheckAnnotation validate false`() {
        signUpForm.isAgree = false
        signUpForm.findAnnotation { annotation -> annotation is RequiredChecked}?.let {
            val validationResult = (it.second as RequiredChecked).annotationValidate(it.first, signUpForm)
            assertFalse(validationResult?.isValid?:true)
        }
    }
}