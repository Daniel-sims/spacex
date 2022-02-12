package com.app.networking.di.modules

import com.app.networking.networking.Networking
import com.app.networking.networking.NetworkingImpl
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal val networkingModule = module {

    single<Networking> {
        NetworkingImpl(
            okHttpClient = get(),
            retrofit = get()
        )
    }

    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .baseUrl("https://api.spacexdata.com/v4/")
            .build()
    }

    single {
        OkHttpClient.Builder().build()
    }
}