package com.example.duncanclark.datasource.api

import com.example.duncanclark.datasource.BuildConfig
import com.example.duncanclark.datasource.api.builders.RetrofitBuildersForTesting
import com.example.duncanclark.datasource.model.DisplayName
import com.example.duncanclark.datasource.model.Place
import com.example.duncanclark.datasource.model.PlacesResponse
import com.example.duncanclark.datasource.model.PrimaryTypeDisplayName
import com.example.duncanclark.domain.model.params.BodyParamsForNearbyPlaces
import com.example.duncanclark.domain.model.params.Center
import com.example.duncanclark.domain.model.params.Circle
import com.example.duncanclark.domain.model.params.FieldMask
import com.example.duncanclark.domain.model.params.IncludedType
import com.example.duncanclark.domain.model.params.LocationRestriction
import com.example.duncanclark.domain.model.params.nearbyPlacesForLunch
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.await

// TODO DC: Test is very brittle since it's using real endpoint and data. Replace with MockResponse().
class NearbyPlacesApiServiceTest {

    // Stub Data
    private val fieldMasks = FieldMask.nearbyPlacesForLunch()
    private val lat = 40.479822043320816
    private val lng = -104.89954079298904
    private val request = BodyParamsForNearbyPlaces(
        locationRestriction = LocationRestriction(
            circle = Circle(Center(lat, lng))
        ),
        includedTypes = IncludedType.nearbyPlacesForLunch(),
        maxResultCount = 1
    )

    // Retrofit, OkHttpClient
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var retrofit: Retrofit

    private lateinit var subject: NearbyPlacesApiService

    @Before
    fun before() {
        okHttpClient = RetrofitBuildersForTesting().getTestOkHttpBuilder()
        retrofit = RetrofitBuildersForTesting().getTestRetrofitBuilder(okHttpClient)
        subject = TestNearbyPlacesApiService(retrofit)
    }

    @Test
    fun `Given Remote API,  When searchNearbyPlaces, Then Return expected place`() = runTest {
        val expected = PlacesResponse(
            listOf(
                Place(
                    name = "places/ChIJzzCIrsKxbocREImaC3XX0lU",
                    rating = 3.2,
                    servesLunch = true,
                    displayName = DisplayName(
                        text = "McDonald's",
                        languageCode = "en"
                    ),
                    allowsDogs = false,
                    servesVegetarianFood = false,
                    formattedAddress = "1061 Main St, Windsor, CO 80550 USA",
                    primaryTypeDisplayName = PrimaryTypeDisplayName(
                        "Fast Food Restaurant",
                        "en-US"
                    )
                )
            )
        )

        try {
            val results = subject.getNearbyPlaces(
                apiKey = BuildConfig.MAPS_API_KEY,
                fieldMask = fieldMasks,
                bodyParams = request
            ).await()
            val actual = results.places.first()
            assertEquals(expected.places.first().name, actual.name)
        } catch (e: Exception) {
            throw (e)
        }
    }

    inner class TestNearbyPlacesApiService(
        private val retrofit: Retrofit
    ): NearbyPlacesApiService {

        private val service by lazy { retrofit.create(NearbyPlacesApiService::class.java) }

        override fun getNearbyPlaces(
            apiKey: String,
            fieldMask: String,
            bodyParams: BodyParamsForNearbyPlaces
        ): Call<PlacesResponse> = service.getNearbyPlaces(
            apiKey,
            fieldMask,
            bodyParams,
        )
    }
}