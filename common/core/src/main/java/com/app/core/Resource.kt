package com.app.core

sealed class Resource<out T, out U> {
    data class Success<out T>(internal val data: T?) : Resource<T, Nothing>()
    data class Error<out U>(internal val errorData: U?) : Resource<Nothing, U>()

    val isSuccessful
        get() = this is Success

    val isError
        get() = this is Error

    val result: T?
        get() = when (this) {
            is Success -> data
            else -> null
        }

    val error: U?
        get() = when (this) {
            is Error -> errorData
            else -> null
        }
}