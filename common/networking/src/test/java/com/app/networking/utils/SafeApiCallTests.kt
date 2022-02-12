package com.app.networking.utils

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.networking.constants.UNKNOWN
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Response
import java.net.HttpURLConnection
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class SafeApiCallTests {

    @ExperimentalCoroutinesApi
    private val unconfinedTestDispatcher = UnconfinedTestDispatcher()

    @Test
    fun `Given when lambda is successful, When invoked, Then successful response returned`() =
        runTest(unconfinedTestDispatcher) {

            // GIVEN
            val func = mockk<() -> Response<Any>>()
            coEvery { func.invoke() } answers { Response.success(mockk()) }

            // WHEN
            val response = safeApiCall { func() }

            // THEN
            Assert.assertTrue(response.isSuccessful)
        }

    @Test
    fun `Given when lambda is unsuccessful, When invoked, Then successful response returned`() =
        runTest(unconfinedTestDispatcher) {

            // GIVEN
            val func = mockk<() -> Response<Any>>()
            coEvery { func.invoke() } answers {
                Response.error(
                    HttpURLConnection.HTTP_BAD_REQUEST,
                    mockk()
                )
            }

            // WHEN
            val response = safeApiCall { func() }

            // THEN
            Assert.assertFalse(response.isSuccessful)
        }

    @Test
    fun `Given when lambda throws unhandled, When invoked, Then correct response is returned`() =
        runTest(unconfinedTestDispatcher) {

            // GIVEN
            val func = mockk<() -> Response<Any>>()
            coEvery { func.invoke() } throws Exception()

            // WHEN
            val response = safeApiCall { func() }

            // THEN
            Assert.assertFalse(response.isSuccessful)
            Assert.assertEquals(UNKNOWN, response.code())
        }
}