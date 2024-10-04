package com.example.duncanclark.datasource.api

import com.example.duncanclark.datasource.model.Place
import com.example.duncanclark.datasource.model.PlacesResponse
import com.example.duncanclark.domain.model.params.Center
import com.example.duncanclark.domain.model.params.Circle
import com.example.duncanclark.domain.model.params.FieldMask
import com.example.duncanclark.domain.model.params.IncludedType
import com.example.duncanclark.domain.model.params.LocationRestriction
import com.example.duncanclark.domain.model.params.NearbyPlaceRequestParams
import com.example.duncanclark.domain.model.params.nearbyPlacesLunch
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// TODO DC: Test is very brittle since it's using real endpoint and data. Replace with MockResponse().
class NearbyPlacesApiServiceTest {

    // Stub Data
    private val fieldMasks = FieldMask.nearbyPlacesLunch()
    private val lat = 40.479822043320816
    private val lng = -104.89954079298904
    private val request = NearbyPlaceRequestParams(
        locationRestriction = LocationRestriction(
            circle = Circle(Center(lat, lng))
        ),
        includedTypes = IncludedType.nearbyPlacesLunch(),
        maxResultCount = 1
    )

    // Retrofit, OkHttpClient
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var retrofit: Retrofit

    private lateinit var subject: NearbyPlacesApiService

    @Before
    fun before() {
        okHttpClient = TestOkHttpBuilder().getOkHttpBuilder()
        retrofit = TestRetrofitBuilder().getRetrofitBuilder(okHttpClient)
        subject = TestNearbyPlacesApiService(retrofit)
    }

    @Test
    fun `Given Remote API,  When searchNearbyPlaces, Then Return expected place`() = runTest {
        val expected = PlacesResponse(
            listOf(
                Place(
                    name="places/ChIJiZF-6jSxbocRwCezM9uGslE",
                    rating=4.5,
                    servesLunch=true
                )
            )
        )

        try {
            val results = subject.searchNearbyPlaces(
                apiKey = "<insert api key here>",
                fieldMask = fieldMasks,
                bodyParams = request
            ).await()
            val actual = results.places.first()
            assertEquals(expected.places.first(), actual)
        } catch (e: Exception) {
            throw (e)
        }
    }

    inner class TestNearbyPlacesApiService(
        private val retrofit: Retrofit
    ): NearbyPlacesApiService {

        private val service by lazy { retrofit.create(NearbyPlacesApiService::class.java) }

        override fun searchNearbyPlaces(
            apiKey: String,
            fieldMask: String,
            bodyParams: NearbyPlaceRequestParams
        ): Call<PlacesResponse> = service.searchNearbyPlaces(
            apiKey,
            fieldMask,
            bodyParams,
        )
    }

    inner class TestOkHttpBuilder {
        fun getOkHttpBuilder(): OkHttpClient {
            return OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
        }
    }

    inner class TestRetrofitBuilder {
        fun getRetrofitBuilder(
            okHttpClient: OkHttpClient
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://places.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }
    }
}