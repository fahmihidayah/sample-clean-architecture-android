package com.widsons.validator

import android.view.View
import android.widget.TextView
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.widsons.validator.test", appContext.packageName)
    }


    @Test
    fun testView() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        var textView = TextView(appContext)
        textView.setText("Hello world")
        assertEquals("Hello world", textView.text.toString())
    }

    @Test
    fun textViewVisibilityVisible() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        var textView = TextView(appContext)
        textView.visibility = View.VISIBLE
        assertEquals(View.VISIBLE, textView.visibility)
    }


}