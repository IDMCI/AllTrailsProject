package com.example.duncanclark.datasource.model

data class PlacesResponse(
    val places: List<Place>,
)

data class Place(
    val name: String,
    val rating: Double?,
    val displayName: DisplayName,
    val servesLunch: Boolean?, // Only returned on NearbyPlaces
)

data class DisplayName(
    val text: String,
    val languageCode: String,
)