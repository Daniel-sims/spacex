package com.app.core

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ResourceTests {

    @Test
    fun `Given resource is successful, When checking type, Then is successful`() {

        // GIVEN
        val resource = Resource.Success(Unit)

        // WHEN
        val isSuccessful = resource.isSuccessful

        // THEN
        Assert.assertTrue(isSuccessful)
    }

    @Test
    fun `Given resource is error, When checking type, Then is error`() {

        // GIVEN
        val resource = Resource.Error(Unit)

        // WHEN
        val isError = resource.isError

        // THEN
        Assert.assertTrue(isError)
    }

    @Test
    fun `Given resource is successful, When data is not null, Then result matches`() {

        // GIVEN
        val exampleData = "example data"

        // WHEN
        val resource = Resource.Success(exampleData)

        // THEN
        Assert.assertEquals(resource.result, exampleData)
    }

    @Test
    fun `Given resource is successful, When data is null, Then result is null`() {

        // GIVEN
        val resource = Resource.Success(null)

        // WHEN
        val result = resource.result

        // THEN
        Assert.assertNull(result)
    }

    @Test
    fun `Given resource is successful, When getting error, Then result is null`() {

        // GIVEN
        val resource = Resource.Success(null)

        // WHEN
        val result = resource.error

        // THEN
        Assert.assertNull(result)
    }

    @Test
    fun `Given resource is error, When getting result, Then result is null `() {

        // GIVEN
        val resource = Resource.Error(null)

        // WHEN
        val result = resource.result

        // THEN
        Assert.assertNull(result)
    }

    @Test
    fun `Given resource is error, When getting error, Then result matches `() {

        // GIVEN
        val exampleError = "Example Error"
        val resource = Resource.Error(exampleError)

        // WHEN
        val result = resource.error

        // THEN
        Assert.assertEquals(exampleError, result)
    }
}