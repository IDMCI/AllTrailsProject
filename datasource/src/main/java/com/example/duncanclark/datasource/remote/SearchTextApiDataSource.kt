package com.example.duncanclark.datasource.remote

import com.example.duncanclark.datasource.api.SearchTextApiService
import com.example.duncanclark.datasource.model.PlacesResponse
import com.example.duncanclark.domain.model.params.ParamsForSearchText
import retrofit2.await
import javax.inject.Inject

interface SearchTextApiDataSource {
    suspend fun search(params: ParamsForSearchText): PlacesResponse
}

class SearchTextApiDataSourceImpl @Inject constructor(
    private val searchTextApiService: SearchTextApiService,
): SearchTextApiDataSource {
    override suspend fun search(params: ParamsForSearchText): PlacesResponse {
        return searchTextApiService.searchText(
            apiKey = "",
            fieldMask = params.fieldMasks,
            bodyParams = params.bodyParams
        ).await()
    }
}