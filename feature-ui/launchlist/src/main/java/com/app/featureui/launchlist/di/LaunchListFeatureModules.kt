package com.app.featureui.launchlist.di

import com.app.featureui.launchlist.di.modules.viewModelModule
import com.app.launchlist.di.launchListApiModule

val launchListFeatureModules = arrayOf(viewModelModule) + launchListApiModule