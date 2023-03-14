package com.widsons.core.data.remote.interceptor

import okhttp3.Interceptor

class InterceptorCollectionsImpl(
    val listInterceptors : List<Interceptor>
) : InterceptorCollections {
    override fun getInterceptors(): List<Interceptor> {
        return listInterceptors
    }
}