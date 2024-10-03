package com.example.duncanclark.domain.common.mapper

interface Mapper <T, R> {
    operator fun invoke(input: T): R
}