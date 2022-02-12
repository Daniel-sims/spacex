package com.app.launchlist.repositories

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.core.dispatcher.TestDispatchProviderImpl
import com.app.launchlist.data.SpacexLaunch
import com.app.launchlist.data.SpacexLaunchError
import com.app.launchlist.mappers.SpacexLaunchMapper
import com.app.launchlist.services.SpacexServices
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.tz.UTCProvider
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Response
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class SpacexLaunchRepositoryImplTests {

    @MockK
    private lateinit var spacexServices: SpacexServices

    @MockK
    private lateinit var spacexLaunchMapper: SpacexLaunchMapper

    private lateinit var spacexLaunchRepository: SpacexLaunchRepository

    private val unconfinedTestDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        DateTimeZone.setProvider(UTCProvider())

        spacexLaunchRepository = SpacexLaunchRepositoryImpl(
            spacexServices = spacexServices,
            spacexLaunchMapper = spacexLaunchMapper,
            dispatchProvider = TestDispatchProviderImpl()
        )
    }

    @Test
    fun `Given an empty list is returned, When getting launch list, Then response is successful with empty list`() =
        runTest(unconfinedTestDispatcher) {

            // GIVEN
            // API & Mapper return empty lists
            coEvery {
                spacexServices.getLaunches()
            } answers {
                Response.success(
                    200,
                    listOf()
                )
            }

            coEvery {
                spacexLaunchMapper.toListOrNull(any())
            } answers {
                listOf()
            }

            // WHEN
            val listResponse = spacexLaunchRepository.getLatestLaunchList()

            // THEN
            Assert.assertTrue(listResponse.isSuccessful)
            Assert.assertNotNull(listResponse.result)
            Assert.assertEquals(0, listResponse.result?.size)
        }

    @Test
    fun `Given a list is returned, When getting launch list, Then response is successful with empty list`() =
        runTest(unconfinedTestDispatcher) {

            // GIVEN
            // API & Mapper return empty lists
            coEvery {
                spacexServices.getLaunches()
            } answers {
                Response.success(
                    200,
                    listOf()
                )
            }

            val itemOne = SpacexLaunch(
                launchName = "Blah",
                launchDate = DateTime.now(),
                launchDateFriendlyName = "Blah",
                successful = false,
                imageUrl = "Blug"
            )
            coEvery {
                spacexLaunchMapper.toListOrNull(any())
            } answers {
                listOf(itemOne)
            }

            // WHEN
            val listResponse = spacexLaunchRepository.getLatestLaunchList()

            // THEN
            Assert.assertTrue(listResponse.isSuccessful)
            Assert.assertNotNull(listResponse.result)
            Assert.assertEquals(1, listResponse.result?.size)

            // This is already asserted by our mapper tests, so this is double coverage and not
            // massively needed, just kind of a nice to have.
            val firstItem = listResponse.result?.firstOrNull()
            Assert.assertEquals(itemOne.launchName, firstItem?.launchName)
            Assert.assertEquals(itemOne.launchDate, firstItem?.launchDate)
            Assert.assertEquals(itemOne.successful, firstItem?.successful)
        }

    @Test
    fun `Given an error, When getting launch list, Then response is error with correct type`() =
        runTest(unconfinedTestDispatcher) {

            // GIVEN
            // API returns an error (doesn't matter in this case)
            coEvery {
                spacexServices.getLaunches()
            } answers {
                Response.error(
                    HttpURLConnection.HTTP_BAD_REQUEST,
                    "".toResponseBody("application/json".toMediaTypeOrNull())
                )
            }

            // Mapper returns bad request
            val expectedError = SpacexLaunchError.BadRequest
            coEvery {
                spacexLaunchMapper.toError(any())
            } answers {
                expectedError
            }

            // WHEN
            val listResponse = spacexLaunchRepository.getLatestLaunchList()

            // THEN
            Assert.assertTrue(listResponse.isError)
            Assert.assertNull(listResponse.result)
            Assert.assertEquals(expectedError, listResponse.error)
        }
}