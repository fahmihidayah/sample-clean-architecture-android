package com.widsons.validator

import android.text.TextWatcher
import android.widget.TextView

class ValidationListenerHolder {
    private var mapEditTextWithListener : MutableMap<TextView, TextWatcher> = mutableMapOf()

    fun put(textView: TextView, textWatcher: TextWatcher) {
        mapEditTextWithListener.put(textView, textWatcher)
    }

    fun onDestroy() {
        for(key in mapEditTextWithListener.keys) {
            mapEditTextWithListener[key]?.let {
                key.removeTextChangedListener(it)
            }
        }
    }

}