package com.example.duncanclark.datasource.repository

import com.example.duncanclark.datasource.model.PlacesResponse
import com.example.duncanclark.datasource.remote.NearbyPlacesApiDataSource
import com.example.duncanclark.domain.common.mapper.Mapper
import com.example.duncanclark.domain.model.ui.LunchPlaces
import com.example.duncanclark.domain.model.params.ParamsForNearbyPlaces
import com.example.duncanclark.domain.model.ui.Places
import com.example.duncanclark.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Named

class NearbyPlacesRepositoryImpl @Inject constructor(
    private val apiDataSource: NearbyPlacesApiDataSource,
    @Named("LunchPlacesMapperImpl")
    private val mapper: Mapper<PlacesResponse, LunchPlaces>,
): Repository<ParamsForNearbyPlaces, Flow<Result<Places>>> {
    override suspend fun execute(params: ParamsForNearbyPlaces): Flow<Result<Places>> = flow {
        try {
            val apiPlaces = apiDataSource.getNearbyPlaces(params)

            emit(Result.success(mapper(apiPlaces)))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}