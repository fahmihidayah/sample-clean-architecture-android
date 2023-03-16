package com.widsons.validator

import android.content.Context
import android.widget.TextView
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {
    @Mock
    private lateinit var context: Context

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testContext() {
        var textView = TextView(context)
        textView.setText("Helloo")

        assertEquals("Helloo", textView.text)
    }
}