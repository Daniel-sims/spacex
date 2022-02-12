package com.app.networking.networking

interface Networking {

    fun <T> createService(clazz: Class<T>): T
}