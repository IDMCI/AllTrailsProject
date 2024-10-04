package com.example.duncanclark.domain.usecase

import com.example.duncanclark.domain.common.mapper.Mapper
import com.example.duncanclark.domain.model.ui.LunchPlaces
import com.example.duncanclark.domain.model.params.ParamsNearbyPlace
import com.example.duncanclark.domain.model.ui.Places
import com.example.duncanclark.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

interface GetNearbyPlacesLunchUseCase {
    suspend fun execute(lat: Double, lng: Double): Flow<Result<Places>>
}

class GetNearbyPlacesLunchUseCaseImpl @Inject constructor(
    private val repository: Repository<ParamsNearbyPlace, Flow<Result<Places>>>,
    private val mapper: Mapper<Pair<Double, Double>, ParamsNearbyPlace>
): GetNearbyPlacesLunchUseCase {
    override suspend fun execute(lat: Double, lng: Double): Flow<Result<Places>> {
        return repository.getData(mapper(Pair(lat, lng)))
    }
}