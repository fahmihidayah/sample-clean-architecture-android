package com.widsons.core.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppSharedPreferences @Inject constructor(
    @ApplicationContext val context: Context
){
    companion object {
        val SHARED_PREF_NAME = "default_shared_pref_application"
    }

//    val masterKey = MasterKey.Builder(context)
//        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
//        .build()

//    val sharedPreferences = EncryptedSharedPreferences
//        .create(
//            context,
//            SHARED_PREF_NAME,
//            masterKey,
//            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
//            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
//        )
    val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

    fun putString(key : String, value : String) = sharedPreferences.edit{
        putString(key, value)
    }

    fun getString(key : String, defaultValue : String = "") = sharedPreferences.getString(key, defaultValue)

    fun putInt(key : String, value : Int) = sharedPreferences.edit {
        putInt(key, value)
    }

    fun getInt(key : String, defaultValue : Int) = sharedPreferences.getInt(key, defaultValue)

}