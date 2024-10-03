package com.example.duncanclark.datasource.repository

import com.example.duncanclark.datasource.model.Place
import com.example.duncanclark.datasource.model.PlacesResponse
import com.example.duncanclark.datasource.remote.NearbyPlacesApiDataSource
import com.example.duncanclark.domain.common.mapper.Mapper
import com.example.duncanclark.domain.model.NearbyPlaceParams
import com.example.duncanclark.domain.model.PointsOfInterest
import com.example.duncanclark.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NearbyPlacesRepositoryImpl @Inject constructor(
    private val apiDataSource: NearbyPlacesApiDataSource,
    private val mapper: Mapper<List<Place>, PointsOfInterest>,
): Repository<NearbyPlaceParams, Flow<Result<PlacesResponse>>> {
    override suspend fun getData(params: NearbyPlaceParams): Flow<Result<PlacesResponse>> = flow {
        try {
            val apiPlaces = apiDataSource.searchNearbyPlaces(params)

            emit(Result.success(apiPlaces))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}