package com.example.duncanclark.datasource.remote

import com.example.duncanclark.datasource.api.NearbyPlacesApiService
import com.example.duncanclark.datasource.model.PlacesResponse
import com.example.duncanclark.domain.model.params.ParamsNearbyPlace
import retrofit2.await
import javax.inject.Inject

interface NearbyPlacesApiDataSource {
    suspend fun searchNearbyPlaces(params: ParamsNearbyPlace): PlacesResponse
}

class NearbyPlacesApiDataSourceImpl @Inject constructor(
    private val nearbyPlacesApiService: NearbyPlacesApiService,
): NearbyPlacesApiDataSource {
    override suspend fun searchNearbyPlaces(params: ParamsNearbyPlace): PlacesResponse {
        return nearbyPlacesApiService.searchNearbyPlaces(
            apiKey = "<insert api key here>",
            fieldMask = params.fieldMasks,
            bodyParams = params.bodyParams
        ).await()
    }
}