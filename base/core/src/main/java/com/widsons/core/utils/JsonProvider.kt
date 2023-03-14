package com.widsons.core.utils

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken


object JsonProvider {

    inline fun <reified U> resToObj(filePath: String): U =
        GsonBuilder().create().fromJson(fileAsString(filePath), object : TypeToken<U>() {}.type)

    fun fileAsString(filePath: String): String = this::class.java
        .getResourceAsStream(filePath)!!
        .bufferedReader()
        .use { it.readText() }
}