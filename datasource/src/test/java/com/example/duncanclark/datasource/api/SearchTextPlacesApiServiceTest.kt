package com.example.duncanclark.datasource.api

import com.example.duncanclark.datasource.BuildConfig
import com.example.duncanclark.datasource.api.builders.RetrofitBuildersForTesting
import com.example.duncanclark.datasource.model.DisplayName
import com.example.duncanclark.datasource.model.Place
import com.example.duncanclark.datasource.model.PlacesResponse
import com.example.duncanclark.domain.model.params.BodyParamsForSearchText
import com.example.duncanclark.domain.model.params.Center
import com.example.duncanclark.domain.model.params.Circle
import com.example.duncanclark.domain.model.params.FieldMask
import com.example.duncanclark.domain.model.params.LocationRestriction
import com.example.duncanclark.domain.model.params.smallerListExample
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.await

// TODO DC: Test is very brittle since it's using real endpoint and data. Replace with MockResponse().
class SearchTextPlacesApiServiceTest {

    // Stub Data
    private val fieldMasks = FieldMask.smallerListExample()
    private val lat = 40.479822043320816
    private val lng = -104.89954079298904
    private val request = BodyParamsForSearchText(
        textQuery = "mCd",
        locationBias = LocationRestriction(
            circle = Circle(Center(lat, lng))
        ),
    )

    // Retrofit, OkHttpClient
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var retrofit: Retrofit

    private lateinit var subject: SearchTextPlacesApiService

    @Before
    fun before() {
        okHttpClient = RetrofitBuildersForTesting().getTestOkHttpBuilder()
        retrofit = RetrofitBuildersForTesting().getTestRetrofitBuilder(okHttpClient)
        subject = TestSearchTextPlacesApiService(retrofit)
    }

    @Test
    fun `Given Remote API,  When searchText, Then Return expected place`() = runTest {
        val expected = PlacesResponse(
            listOf(
                Place(
                    name = "places/ChIJzzCIrsKxbocREImaC3XX0lU",
                    rating = 3.2,
                    servesLunch = null,
                    displayName = DisplayName(
                        text = "McDonald's",
                        languageCode = "en"
                    )
                )
            )
        )

        try {
            val results = subject.searchText(
                apiKey = BuildConfig.MAPS_API_KEY,
                fieldMask = fieldMasks,
                bodyParams = request
            ).await()
            val actual = results.places.first()
            assertEquals(expected.places.first(), actual)
        } catch (e: Exception) {
            throw (e)
        }
    }

    inner class TestSearchTextPlacesApiService(
        private val retrofit: Retrofit
    ): SearchTextPlacesApiService {

        private val service by lazy { retrofit.create(SearchTextPlacesApiService::class.java) }

        override fun searchText(
            apiKey: String,
            fieldMask: String,
            bodyParams: BodyParamsForSearchText
        ): Call<PlacesResponse> {
            return service.searchText(
                apiKey = apiKey,
                fieldMask = fieldMask,
                bodyParams = bodyParams,
            )
        }
    }
}