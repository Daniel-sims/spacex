package com.app.networking.utils

import com.app.networking.constants.UNKNOWN
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

suspend fun <T> safeApiCall(function: suspend () -> (Response<T>)): Response<T> = try {
    function.invoke()
} catch (ex: Exception) {
    Response.error(
        UNKNOWN,
        "".toResponseBody()
    )
}
