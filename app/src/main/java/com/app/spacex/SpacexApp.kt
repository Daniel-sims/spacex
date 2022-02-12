package com.app.spacex

import android.app.Application
import com.app.networking.di.networkingModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class SpacexApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SpacexApp)
            modules(
                listOf(
                    *networkingModules,
                )
            )
        }


    }
}