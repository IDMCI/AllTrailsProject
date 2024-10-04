package com.example.duncanclark.datasource.remote

import com.example.duncanclark.datasource.api.SearchTextPlacesApiService
import com.example.duncanclark.datasource.model.PlacesResponse
import com.example.duncanclark.domain.model.params.ParamsForSearchTextPlaces
import retrofit2.await
import javax.inject.Inject

interface SearchTextApiDataSource {
    suspend fun search(params: ParamsForSearchTextPlaces): PlacesResponse
}

class SearchTextApiDataSourceImpl @Inject constructor(
    private val searchTextPlacesApiService: SearchTextPlacesApiService,
): SearchTextApiDataSource {
    override suspend fun search(params: ParamsForSearchTextPlaces): PlacesResponse {
        return searchTextPlacesApiService.searchText(
            apiKey = "",
            fieldMask = params.fieldMasks,
            bodyParams = params.bodyParams
        ).await()
    }
}