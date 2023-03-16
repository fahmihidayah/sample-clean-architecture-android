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
class EqualValidatorTest {

    var signUpForm = SignUpForm(
        email = "",
        name = "",
        password = "12345",
        confirmPassword = "12345"
    )

    @Test
    fun `equalAnnotation validate true`() {
        signUpForm.findAnnotation { annotation -> annotation is Equal  }?.let {
            var validationResult = (it.second as Equal).annotationValidate(it.first, signUpForm)
            assertTrue(validationResult?.isValid?:false)
        }
    }

    @Test
    fun `equalAnnotation validate false`() {
        signUpForm.confirmPassword = "122223"
        signUpForm.findAnnotation { annotation -> annotation is Equal }?.let {
            assertFalse((it.second as Equal).annotationValidate(it.first, signUpForm)?.isValid?:true)
        }
    }


}