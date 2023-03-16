package com.widsons.validator.utils

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created on : June/25/2021
 * Author     : Muhammad Fahmi Hidayah
 * Company    : PiXilApps
 * Project    : TodoApps
 */
@RunWith(AndroidJUnit4::class)
class ViewValidationXKtTest {

    @Test
    fun check() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.widsons.validator.test", appContext.packageName)
    }

}