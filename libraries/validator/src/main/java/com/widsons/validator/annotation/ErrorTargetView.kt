package com.widsons.validator.annotation

import androidx.annotation.IdRes
import androidx.annotation.Nullable

/**
 * Created on : December/23/2020
 * Author     : Muhammad Fahmi Hidayah
 * Company    : PiXilApps
 * Project    : Yemnak
 */

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class ErrorTargetView (
    @Nullable val errorTargetId : Int = -1
)