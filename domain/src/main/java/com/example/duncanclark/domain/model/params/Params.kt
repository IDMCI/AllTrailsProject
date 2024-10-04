package com.example.duncanclark.domain.model.params

import kotlinx.serialization.SerialName

// NearbyPlaces
data class ParamsForNearbyPlaces(
    val fieldMasks: String,
    val bodyParams: BodyParamsForNearbyPlaces,
)

data class BodyParamsForNearbyPlaces(
    @SerialName("locationRestriction") val locationRestriction: LocationRestriction,
    @SerialName("includedTypes") val includedTypes: String,
    @SerialName("maxResultCount") val maxResultCount: Int = 20, // Max number is between 1 and 20
)

// SearchText
data class ParamsForSearchTextPlaces(
    val fieldMasks: String,
    val bodyParams: BodyParamsForSearchText,
)

data class BodyParamsForSearchText(
    @SerialName("textQuery") val textQuery: String,
    @SerialName("locationBias") val locationRestriction: LocationRestriction,
)

// Common
data class LocationRestriction(
    @SerialName("circle") val circle: Circle,
)

data class Circle (
    @SerialName("center") val center: Center,
    @SerialName("radius") val radius: Double = 5000.0, // Meters
)

data class Center(
    @SerialName("latitude") val latitude: Double,
    @SerialName("latitude") val longitude: Double,
)