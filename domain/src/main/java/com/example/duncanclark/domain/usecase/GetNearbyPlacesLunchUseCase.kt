package com.example.duncanclark.domain.usecase

import com.example.duncanclark.domain.common.mapper.Mapper
import com.example.duncanclark.domain.model.ui.LunchPlaces
import com.example.duncanclark.domain.model.params.ParamsNearbyPlace
import com.example.duncanclark.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

interface GetNearbyPlacesLunchUseCase {
    suspend fun execute(lat: Double, lng: Double): Flow<Result<LunchPlaces>>
}

class GetNearbyPlacesLunchUseCaseImpl @Inject constructor(
    @Named("NearbyPlacesRepositoryImpl")
    private val repository: Repository<ParamsNearbyPlace, Flow<Result<LunchPlaces>>>,
    @Named("ParamsNearbyPlaceMapperImpl")
    private val mapper: Mapper<Pair<Double, Double>, ParamsNearbyPlace>
): GetNearbyPlacesLunchUseCase {
    override suspend fun execute(lat: Double, lng: Double): Flow<Result<LunchPlaces>> {
        return repository.getData(mapper(Pair(lat, lng)))
    }
}