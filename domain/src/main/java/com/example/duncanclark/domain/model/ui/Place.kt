package com.example.duncanclark.domain.model.ui

typealias Places = List<Place>
typealias LunchPlaces = List<Place.LunchPlace>
typealias PlaceId = String

sealed class Place: BasePlace {
    data class LunchPlace(
        override val placeId: PlaceId,
        override val displayName: String,
        val rating: Double?,
        val servesLunch: Boolean,
        override val languageCode: String?,
        override val isSelected: Boolean = false,
    ): Place()
}

internal interface BasePlace {
    val placeId: PlaceId
    val displayName: String
    val languageCode: String?
    val isSelected: Boolean
}