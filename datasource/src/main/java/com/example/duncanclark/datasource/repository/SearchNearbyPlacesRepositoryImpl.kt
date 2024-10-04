package com.example.duncanclark.datasource.repository

import com.example.duncanclark.datasource.model.PlacesResponse
import com.example.duncanclark.datasource.remote.SearchTextApiDataSource
import com.example.duncanclark.domain.common.mapper.Mapper
import com.example.duncanclark.domain.model.params.ParamsForSearchText
import com.example.duncanclark.domain.model.ui.LunchPlaces
import com.example.duncanclark.domain.model.ui.Places
import com.example.duncanclark.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchNearbyPlacesRepositoryImpl @Inject constructor(
    private val apiDataSource: SearchTextApiDataSource,
    private val mapper: Mapper<PlacesResponse, LunchPlaces>,
): Repository<ParamsForSearchText, Flow<Result<Places>>> {
    override suspend fun execute(
        params: ParamsForSearchText
    ): Flow<Result<Places>> = flow {
        try {
            val apiPlaces = apiDataSource.search(params)

            emit(Result.success(mapper(apiPlaces)))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}