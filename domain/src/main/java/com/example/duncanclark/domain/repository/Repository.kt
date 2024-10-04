package com.example.duncanclark.domain.repository

interface Repository<T, R> {
    suspend fun getData(params: T): R
}