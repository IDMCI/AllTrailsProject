package com.example.duncanclark.datasource.api

import com.example.duncanclark.datasource.model.PlacesResponse
import com.example.duncanclark.domain.model.NearbyPlaceRequestParams
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface NearbyPlacesApiService {
    @POST("v1/places:searchNearby")
    @Headers("Content-Type: application/json")
    fun searchNearbyPlaces(
        @Header("X-Goog-Api-key") apiKey: String,
        @Header("X-Goog-FieldMask") fieldMask: String,
        @Body request: NearbyPlaceRequestParams,
    ): Call<PlacesResponse>
}