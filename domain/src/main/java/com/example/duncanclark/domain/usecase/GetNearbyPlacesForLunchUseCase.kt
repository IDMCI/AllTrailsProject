package com.example.duncanclark.domain.usecase

import com.example.duncanclark.domain.common.mapper.Mapper
import com.example.duncanclark.domain.model.params.ParamsForNearbyPlaces
import com.example.duncanclark.domain.model.ui.Places
import com.example.duncanclark.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

interface GetNearbyPlacesLunchUseCase {
    suspend fun execute(lat: Double, lng: Double): Flow<Result<Places>>
}

class GetNearbyPlacesLunchUseCaseImpl @Inject constructor(
    @Named("NearbyPlacesRepositoryImpl")
    private val repository: Repository<ParamsForNearbyPlaces, Flow<Result<Places>>>,
    @Named("ParamsNearbyPlaceMapperImpl")
    private val mapper: Mapper<Pair<Double, Double>, ParamsForNearbyPlaces>
): GetNearbyPlacesLunchUseCase {
    override suspend fun execute(lat: Double, lng: Double): Flow<Result<Places>> {
        return repository.execute(mapper(Pair(lat, lng)))
    }
}