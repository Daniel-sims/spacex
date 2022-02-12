package com.app.networking.utils

import com.app.networking.networking.Networking

fun <T> createService(
    networking: Networking,
    clazz: Class<T>
): T = networking.createService(clazz)