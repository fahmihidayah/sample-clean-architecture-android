package com.widsons.core.data.local

import javax.inject.Inject
import javax.inject.Singleton

abstract class AppManager {
    abstract var authKey : String

    abstract var apiKey : String

    abstract var language : String

}