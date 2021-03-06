package com.app.networking.networking

import okhttp3.OkHttpClient
import retrofit2.Retrofit

internal class NetworkingImpl(
    private val okHttpClient: OkHttpClient,
    private val retrofit: Retrofit
) : Networking {

    override fun <T> createService(
        clazz: Class<T>
    ): T = retrofit
        .newBuilder()
        .client(okHttpClient)
        .build()
        .create(clazz)
}