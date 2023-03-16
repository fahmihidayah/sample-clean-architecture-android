package com.widsons.validator.data

import android.content.Context

/**
 * Created on : September/06/2021
 * Author     : Muhammad Fahmi Hidayah
 * Company    : PiXilApps
 * Project    : Yemnak
 */
class ValidationResultWithExtraMessage(
    fieldName : String = "",
    errorRes: Int = -1,
    error: String = "",
    targetId: Int = -1,
    errorViewId: Int = -1,
    isValid: Boolean = false,
    val extraMessage : String = "",
) : ValidationResult(fieldName, errorRes, error, targetId, errorViewId, isValid) {

    override fun getMessage(context: Context?): String {
        if(errorRes == -1) {
            return error
        }
        else {
            return context?.getString(errorRes, extraMessage)?:""
        }
    }
}