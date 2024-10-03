package com.example.duncanclark.domain.usecase

import javax.inject.Inject

interface GetNearbyPlacesLunchUseCase {
    // TODO: Add LatLng parameter later.
//    suspend fun execute(): Flow<Result<Places>>
}

class GetNearbyPlacesLunchUseCaseImpl @Inject constructor(
//    private val repository: Repository<Flow<Result<Places>>>
): GetNearbyPlacesLunchUseCase {
//    override suspend fun execute(): Flow<Result<Places>> {
//        return repository.getData()
//    }
}