package com.example.duncanclark.datasource.remote

import com.example.duncanclark.datasource.api.NearbyPlacesApiService
import com.example.duncanclark.datasource.model.PlacesResponse
import com.example.duncanclark.domain.model.NearbyPlaceParams
import com.google.gson.Gson
import retrofit2.await
import javax.inject.Inject

interface NearbyPlacesApiDataSource {
    suspend fun searchNearbyPlaces(params: NearbyPlaceParams): PlacesResponse
}

class NearbyPlacesApiDataSourceImpl @Inject constructor(
    private val nearbyPlacesApiService: NearbyPlacesApiService,
    private val gson: Gson,
): NearbyPlacesApiDataSource {
    override suspend fun searchNearbyPlaces(params: NearbyPlaceParams): PlacesResponse {
        val fieldMask = gson.toJson(params.fieldMasks)
        return nearbyPlacesApiService.searchNearbyPlaces(
            apiKey = "<insert api key here>",
            fieldMask = fieldMask,
            request = params.bodyParams
        ).await()
    }
}