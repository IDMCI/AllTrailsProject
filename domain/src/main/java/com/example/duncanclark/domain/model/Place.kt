package com.example.duncanclark.domain.model

typealias PointsOfInterest = List<Poi>
typealias LunchPlaces = List<Poi.LunchPlace>

sealed class Poi {
    data class LunchPlace(
        val id: String,
        val displayName: String,
        val languageCode: String,
        val rating: Float,
        val servesLunch: Boolean,
    ): Poi()
}