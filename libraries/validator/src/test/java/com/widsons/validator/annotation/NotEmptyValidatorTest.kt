package com.widsons.validator.annotation

import com.widsons.validator.sampleObject.SignUpForm
import com.widsons.validator.utils.findAnnotation
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created on : June/21/2021
 * Author     : Muhammad Fahmi Hidayah
 * Company    : PiXilApps
 * Project    : TodoApps
 */
@RunWith(MockitoJUnitRunner::class)
class NotEmptyValidatorTest {

    var signUpForm  = SignUpForm(
        email = "",
        name = "fahmi",
        password = "",
        confirmPassword = ""
    )

    @Test
    fun `notEmptyAnnotation validate true`() {
        signUpForm.name = "fahmi"
        signUpForm.findAnnotation { annotation -> annotation is NotEmpty }?.let {
            var validationResult = (it.second as NotEmpty).annotationValidate(it.first, signUpForm)
            assertTrue(validationResult?.isValid?:false)
        }
    }

    @Test
    fun `notEmptyAnnotation validate false`() {
        signUpForm.name = ""
        signUpForm.findAnnotation {  annotation -> annotation is NotEmpty }?.let {
            val validationResult = (it.second as NotEmpty).annotationValidate(it.first, signUpForm)
            assertFalse(validationResult?.isValid?:true)
        }
    }

}