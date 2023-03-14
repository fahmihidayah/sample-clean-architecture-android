package com.widsons.core.data.local

class AppManagerImpl constructor(
    private val prefs: AppSharedPreferences
) : AppManager() {

    override var authKey : String
        set(value) {
            prefs.putString("auth_key", value)
        }
        get() = prefs.getString("auth_key", "") ?: ""

    override  var apiKey : String
        set(value) {
            prefs.putString("api_key", value)
        }
        get() = prefs.getString("api_key", "") ?: ""

    override var language : String
        set(value) {
            prefs.putString("language", value)
        }
        get() = prefs.getString("language", "") ?: ""
}