package com.widsons.validator.rules

import com.widsons.validator.ValidateField
import com.widsons.validator.utils.validateField
import org.junit.Assert.*
import org.junit.Test

class RegexRuleTest {
    @Test
    fun testRegexPhoneKuwaitTrue() {
        val field = ValidateField(
            rules = mutableListOf(
                RegexRule(regex = "^[9645]\\d{7}")
            )
        )
        field.fieldValue = "91231234"
        assertTrue( field.validateField().firstOrNull()?.isValid?:false)
    }
}