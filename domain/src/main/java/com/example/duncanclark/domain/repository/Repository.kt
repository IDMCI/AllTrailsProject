package com.example.duncanclark.domain.repository

interface Repository<T, R> {
    suspend fun execute(params: T): R
}