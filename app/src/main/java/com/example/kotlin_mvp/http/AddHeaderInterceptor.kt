package com.example.kotlin_mvp.http

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AddHeaderInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder()
            //.header("key", "value")
            .header("Content-Type", "application/json;charset=UTF-8")
            .method(original.method(), original.body())
            .build()
        return chain.proceed(request)
    }
}