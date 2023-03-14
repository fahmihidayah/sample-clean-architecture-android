package com.widsons.core.data.remote.interceptor

import com.widsons.core.data.local.AppManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptorImpl(val appManager: AppManager) : AuthInterceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder().apply {
            addHeader("Accept-Language", appManager.language)
            addHeader("apikey", appManager.apiKey)
            addHeader("CustomAuth", "Bearer ${appManager.authKey}")
        }

        return chain.proceed(requestBuilder.build())
    }
}