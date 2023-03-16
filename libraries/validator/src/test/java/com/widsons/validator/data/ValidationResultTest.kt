package com.widsons.validator.data

import org.junit.Assert.*
import org.junit.Test

/**
 * Created on : June/21/2021
 * Author     : Muhammad Fahmi Hidayah
 * Company    : PiXilApps
 * Project    : TodoApps
 */
class ValidationResultTest {

    @Test
    fun `test validation result get messaage `() {
        var validationResult : ValidationResult = ValidationResult()
        assertEquals("Error message", validationResult.getMessage(null))
    }
}