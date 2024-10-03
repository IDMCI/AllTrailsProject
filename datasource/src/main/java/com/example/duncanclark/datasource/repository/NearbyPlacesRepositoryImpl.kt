package com.example.duncanclark.datasource.repository

import com.example.duncanclark.datasource.api.NearbyPlacesApiService
import com.example.duncanclark.datasource.model.Place
import com.example.duncanclark.domain.common.mapper.Mapper
import com.example.duncanclark.domain.model.PointsOfInterest
import com.example.duncanclark.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.await
import javax.inject.Inject

class NearbyPlacesRepositoryImpl @Inject constructor(
    private val nearbyPlacesApiService: NearbyPlacesApiService,
    private val mapper: Mapper<List<Place>, PointsOfInterest>,
): Repository<Flow<Result<List<Place>>>> {
    override suspend fun getData(): Flow<Result<List<Place>>> = flow {
        try {
            val apiPlaces = nearbyPlacesApiService.searchNearbyPlaces().await()
            emit(Result.success(apiPlaces))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}