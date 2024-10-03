package com.example.duncanclark.domain.model

typealias PointsOfInterest = List<Poi>

data class Poi(
    val id: String,
    val displayName: String,
    val languageCode: String,
    val rating: Float,
    val servesLunch: Boolean,
)