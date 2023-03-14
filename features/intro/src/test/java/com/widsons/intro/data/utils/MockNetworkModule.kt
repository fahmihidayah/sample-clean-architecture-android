package com.widsons.intro.data.utils

import com.widsons.core.data.remote.interceptor.InterceptorCollectionsImpl
import com.widsons.core.di.NetworkModule
import okhttp3.Interceptor

import okhttp3.mock.ClasspathResources.resource

import okhttp3.mock.*
import retrofit2.Retrofit
import java.io.IOException

class MockNetworkModule {

    private val networkModule: NetworkModule = NetworkModule()

    fun provideSuccessInterceptor(): MockInterceptor {
        return MockInterceptor().apply {
            rule(get, url eq "${networkModule.BASE_URL}configurations") {
                respond(
                    resource("v1/success_fine_response.json"),
                    MediaTypes.MEDIATYPE_JSON
                )
            }
        }
    }

    fun provideFailureIOInterceptor(): MockInterceptor {
        return MockInterceptor().apply {
            rule(get, url eq "${networkModule.BASE_URL}configurations") {
                respond {
                    throw IOException("an IO Error")
                }
            }
        }
    }

    fun provideFailureResponseInterceptor(): MockInterceptor {
        return MockInterceptor().apply {
            rule(get, url eq "${networkModule.BASE_URL}configurations") {
                respond( code = HttpCode.HTTP_404_NOT_FOUND ) {
                    body("v1/failure_response.json")
                }
            }
        }
    }


    fun providesRetrofit(interceptors: List<Interceptor>): Retrofit {
        return networkModule.providesRetrofit(
            networkModule.providesOkHttpClient(
                InterceptorCollectionsImpl(
                    interceptors
                )
            )
        )
    }

    fun providesRetrofitSuccess() = providesRetrofit(
        mutableListOf(provideSuccessInterceptor())
    )

    fun providesRetrofitFailure() = providesRetrofit(
        mutableListOf(provideFailureIOInterceptor())
    )

    fun providesRetrofitResponseFailure() = providesRetrofit(
        mutableListOf(provideFailureResponseInterceptor())
    )

}