package com.example.duncanclark.domain.usecase

import kotlinx.coroutines.flow.Flow

interface UseCaseWithParam<in T, out R> {
    suspend fun execute(input: T): Flow<Result<R>>
}