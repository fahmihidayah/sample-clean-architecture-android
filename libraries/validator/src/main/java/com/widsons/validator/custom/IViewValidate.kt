package com.widsons.validator.custom

/**
 * Created on : June/25/2021
 * Author     : Muhammad Fahmi Hidayah
 * Company    : PiXilApps
 * Project    : TodoApps
 */
interface IViewValidate {
    fun onSetErrorMessage(message : String)
    fun onShowError()
    fun onHideError()
}