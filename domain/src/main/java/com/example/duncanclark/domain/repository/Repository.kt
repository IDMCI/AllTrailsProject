package com.example.duncanclark.domain.repository

interface Repository<T> {
    suspend fun getData(): T
}