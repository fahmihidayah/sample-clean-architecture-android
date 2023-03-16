package com.widsons.validator.utils

import com.widsons.validator.sampleObject.SignUpForm
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
class ValidationXKtTest {

    var signUpForm : SignUpForm = SignUpForm(
        email = "",
        name = "",
        password = "",
        confirmPassword = ""
    )

    @Test
    fun test_name_not_empty_true() {
        signUpForm.name = "fahmi"
        signUpForm.validateForm().find { it.fieldName == "name" }?.isValid?.let {
            assertTrue(it)
        }
    }

    @Test
    fun test_email_correct_true() {
        signUpForm.email = "fahmi@gmail.com"
        signUpForm.validateForm().find { it.fieldName == "email" }?.isValid?.let {
            assertTrue(it)
        }
    }

    @Test
    fun test_password_min_5_true() {
        signUpForm.password = "12345"
        signUpForm.validateForm().find { it.fieldName == "password" }?.isValid?.let {
            assertTrue(it)
        }
    }

    @Test
    fun test_confirm_password_equals_password_true() {
        signUpForm.password = "12345"
        signUpForm.confirmPassword = "12345"
        signUpForm.validateForm().find { it.fieldName == "confirmPassword" }?.isValid?.let {
            assertTrue(it)
        }
    }

    @Test
    fun test_is_agree_req_true_true() {
        signUpForm.isAgree = true
        signUpForm.validateForm().find { it.fieldName == "isAgree" }?.isValid?.let {
            assertTrue(it)
        }
    }

//    @Test
//    fun getTargetView() {
//        assertNotNull(signUpForm.getFields()[0].getTargetView())
//    }

//    @Test
//    fun `get target view id`() {
//        assertEquals(1, signUpForm.getFields()[0].getTargetView().getId())
//    }

//    @Test
//    fun `get error target view id`() {
//        assertEquals(1, signUpForm.getFields()[0].getErrorTargetView().getId())
//    }

//    @Test
//    fun `findAnnotation email found`() {
//        assertNotNull(signUpForm.findAnnotation {annotation -> annotation is Email })
//    }

    @Test
    fun `validateMethod validateObject size5`() {
        signUpForm.email = "fahmi@gmail.com"
        signUpForm.name = "fahmi"
        signUpForm.password = "123456"
        signUpForm.confirmPassword = "123456"
        signUpForm.isAgree = true
        assertEquals(5, signUpForm.validateForm().size)
    }
//
    @Test
    fun `isValidMethod validateObject true`() {
        signUpForm.email = "fahmi@gmail.com"
        signUpForm.name = "fahmi"
        signUpForm.password = "123456"
        signUpForm.confirmPassword = "123456"
        signUpForm.isAgree = true
        assertTrue( signUpForm.validateForm().isValid())
    }
//
    @Test
    fun `isValidMethod validateObject false`() {
        signUpForm.email = "fahminotemail"
        signUpForm.name = "fahmi"
        signUpForm.password = "123456"
        signUpForm.confirmPassword = "123456"
        signUpForm.isAgree = true
        assertFalse( signUpForm.validateForm().isValid())
    }

    @Test
    fun fill_form_value_true() {
        signUpForm.fillFormValue()
    }
//
//    @Test
//    fun `findField findVField true`(){
//        assertNotNull(signUpForm.getField("email"))
//    }

}