package com.example.duncanclark.domain.model.ui

typealias Places = List<Place>
typealias LunchPlaces = List<Place.LunchPlace>

sealed class Place {
    data class LunchPlace(
        val id: String,
        val displayName: String = "",
        val languageCode: String = "",
        val rating: Double?,
        val servesLunch: Boolean,
    ): Place()
}