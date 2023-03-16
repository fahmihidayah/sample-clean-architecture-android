package com.widsons.validator

import android.os.Parcelable
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import com.widsons.validator.rules.Rule
import kotlinx.android.parcel.Parcelize

@Parcelize
open class ValidateField (
    var fieldName : String = "",
    @IdRes var viewId :  Int = -1,
    @IdRes var errorId : Int = -1,
    @StringRes var errorRes : Int = -1,
    var error : String = "error",
    var rules : MutableList<Rule> = mutableListOf()
) : Parcelable {
    var fieldValue : Any? = null
}