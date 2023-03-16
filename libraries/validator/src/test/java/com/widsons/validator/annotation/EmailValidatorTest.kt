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
class EmailValidatorTest {

    var signUpForm = SignUpForm(
        email = "",
        name = "",
        password = "",
        confirmPassword = ""
    )

    @Test
    fun `emailAnnotation validate true`() {
        signUpForm.email = "fahmi@gmail.com"
        signUpForm.findAnnotation { annotation -> annotation is Email  }?.let {
            var validationResult = (it.second as Email).annotationValidate(it.first, signUpForm)
            assertTrue(validationResult?.isValid?:false)
        }
    }

    @Test
    fun `emailAnnotation validate false`() {
        signUpForm.findAnnotation { annotation -> annotation is Email }?.let {
            assertFalse((it.second as Email).annotationValidate(it.first, signUpForm)?.isValid?:true)
        }
    }


}