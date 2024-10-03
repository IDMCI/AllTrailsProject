package com.example.duncanclark.domain.usecase

import javax.inject.Inject

interface GetNearbyPlacesUseCase {
    // TODO: Add LatLng parameter later.
//    suspend fun execute(): Flow<Result<Places>>
}

class GetNearbyPlacesUseCaseImpl @Inject constructor(
//    private val repository: Repository<Flow<Result<Places>>>
): GetNearbyPlacesUseCase {
//    override suspend fun execute(): Flow<Result<Places>> {
//        return repository.getData()
//    }
}