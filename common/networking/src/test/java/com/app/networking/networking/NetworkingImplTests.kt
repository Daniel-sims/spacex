package com.app.networking.networking

import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.MockKAnnotations
import okhttp3.OkHttpClient
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit

@RunWith(AndroidJUnit4::class)
class NetworkingImplTests {

    private val okHttpClient = OkHttpClient()
    private val retrofit = Retrofit.Builder().baseUrl("https://www.example.com").build()

    private lateinit var networking: Networking

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        networking = NetworkingImpl(
            okHttpClient = okHttpClient,
            retrofit = retrofit
        )
    }

    interface ExampleService

    @Test
    fun `When creating service, Service not null`() {
        Assert.assertNotNull(
            networking.createService(
                clazz = ExampleService::class.java
            )
        )
    }
}