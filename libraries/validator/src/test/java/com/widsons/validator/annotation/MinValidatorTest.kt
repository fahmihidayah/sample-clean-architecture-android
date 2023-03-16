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
class MinValidatorTest {

    var signUpForm  = SignUpForm(
        email = "",
        name = "",
        password = "",
        confirmPassword = ""
    )

    @Test
    fun `minAnnotation validate true`() {
        signUpForm.password = "123456"
        signUpForm.findAnnotation { annotation -> annotation is Min }?.let {
            val validationResult = (it.second as Min).annotationValidate(it.first, signUpForm)
            assertTrue(validationResult?.isValid?:false)
        }
    }

    @Test
    fun `minAnnotation validate false`() {
        signUpForm.password = "1"
        signUpForm.findAnnotation { annotation -> annotation is Min}?.let {
            val validationResult = (it.second as Min).annotationValidate(it.first, signUpForm)
            assertFalse(validationResult?.isValid?:true)
        }
    }
}