package com.app.launchlist.mappers

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.launchlist.responses.SpacexLaunchResponse
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.tz.UTCProvider
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SpacexLaunchMapperTests {

    @Before
    fun setup() {
        DateTimeZone.setProvider(UTCProvider()) // JodaTime requires this to remove a warning.
    }

    @Test
    fun `Given list is null, When mapping to list, Then null is returned`() {
        val mapper = SpacexLaunchMapper()

        // GIVEN
        val list = null

        // WHEN
        val response = mapper.toListOrNull(list)


        // THEN
        Assert.assertNull(response)
    }

    @Test
    fun `Given list contains items, When mapping to list, Then list is returned`() {
        val mapper = SpacexLaunchMapper()

        // GIVEN
        val item = SpacexLaunchResponse(
            name = "Blah",
            launchDateUtc = "2020-05-22T15:00:00.000Z",
            success = true
        )
        val list = listOf(item)

        // WHEN
        val response = mapper.toListOrNull(list)

        // THEN
        Assert.assertEquals(1, response?.size)

        val mappedItem = response?.firstOrNull()

        Assert.assertEquals(item.name, mappedItem?.launchName)
        Assert.assertEquals(DateTime.parse(item.launchDateUtc), mappedItem?.launchDate)
        Assert.assertEquals(item.success, mappedItem?.successful)
    }

    @Test
    fun `Given list contains item with malformed date, When mapping to list, Then item is not returned`() {
        val mapper = SpacexLaunchMapper()

        // GIVEN
        val itemWithMalformedDate = SpacexLaunchResponse(
            name = "Blah",
            launchDateUtc = "This won't work",
            success = true
        )
        val item = SpacexLaunchResponse(
            name = "Blah",
            launchDateUtc = "2020-05-22T15:00:00.000Z",
            success = true
        )
        val list = listOf(itemWithMalformedDate, item)

        // WHEN
        val response = mapper.toListOrNull(list)

        // THEN
        Assert.assertEquals(1, response?.size)

        val mappedItem = response?.firstOrNull()

        Assert.assertEquals(item.name, mappedItem?.launchName)
        Assert.assertEquals(DateTime.parse(item.launchDateUtc), mappedItem?.launchDate)
        Assert.assertEquals(item.success, mappedItem?.successful)
    }
}