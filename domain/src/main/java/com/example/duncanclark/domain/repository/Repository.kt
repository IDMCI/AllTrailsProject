package com.example.duncanclark.domain.repository

interface Repository<in T, out R> {
    suspend fun getData(params: T): R
}