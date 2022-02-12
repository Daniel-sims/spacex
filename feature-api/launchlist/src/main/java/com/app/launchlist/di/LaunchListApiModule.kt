package com.app.launchlist.di

import com.app.launchlist.di.modules.dataModule
import com.app.launchlist.di.modules.networkModule

val launchListApiModule = arrayOf(dataModule, networkModule)