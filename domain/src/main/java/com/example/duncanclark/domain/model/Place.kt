package com.example.duncanclark.domain.model

typealias Places = List<Place>

data class Place(
    val id: String,
    val displayName: String,
    val languageCode: String,
    val rating: Float,
    val servesLunch: Boolean,
)