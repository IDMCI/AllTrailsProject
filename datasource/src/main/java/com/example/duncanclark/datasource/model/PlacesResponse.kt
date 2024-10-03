package com.example.duncanclark.datasource.model

data class PlacesResponse(
    val places: List<Place>,
)

data class Place(
    val name: String,
    val rating: Double?,
    val servesLunch: Boolean,
)

data class DisplayName(
    val text: String,
    val languageCode: String,
)