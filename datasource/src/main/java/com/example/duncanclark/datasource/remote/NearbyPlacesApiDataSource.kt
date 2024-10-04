package com.example.duncanclark.datasource.remote

import com.example.duncanclark.datasource.BuildConfig
import com.example.duncanclark.datasource.api.NearbyPlacesApiService
import com.example.duncanclark.datasource.model.PlacesResponse
import com.example.duncanclark.domain.model.params.ParamsForNearbyPlaces
import retrofit2.await
import javax.inject.Inject

interface NearbyPlacesApiDataSource {
    suspend fun getNearbyPlaces(params: ParamsForNearbyPlaces): PlacesResponse
}

class NearbyPlacesApiDataSourceImpl @Inject constructor(
    private val nearbyPlacesApiService: NearbyPlacesApiService,
): NearbyPlacesApiDataSource {
    override suspend fun getNearbyPlaces(params: ParamsForNearbyPlaces): PlacesResponse {
        return nearbyPlacesApiService.getNearbyPlaces(
            apiKey = BuildConfig.MAPS_API_KEY,
            fieldMask = params.fieldMasks,
            bodyParams = params.bodyParams
        ).await()
    }
}