package com.example.duncanclark.domain.usecase

import com.example.duncanclark.domain.common.mapper.Mapper
import com.example.duncanclark.domain.model.params.ParamsForSearchTextPlaces
import com.example.duncanclark.domain.model.ui.Places
import com.example.duncanclark.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface SearchTextLunchUseCase {
    suspend fun execute(textQuery: String): Flow<Result<Places>>
}

class SearchTextLunchUseCaseImpl @Inject constructor(
    private val repository: Repository<ParamsForSearchTextPlaces, Flow<Result<Places>>>,
    private val mapper: Mapper<String, ParamsForSearchTextPlaces>
): SearchTextLunchUseCase {
    override suspend fun execute(textQuery: String): Flow<Result<Places>> {
        return repository.execute(mapper(textQuery))
    }
}