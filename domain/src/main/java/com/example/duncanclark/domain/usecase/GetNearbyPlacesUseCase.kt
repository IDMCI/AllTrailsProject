package com.example.duncanclark.domain.usecase

import com.example.duncanclark.domain.model.Places
import com.example.duncanclark.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
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