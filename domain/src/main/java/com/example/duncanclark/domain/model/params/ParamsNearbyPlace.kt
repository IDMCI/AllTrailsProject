package com.example.duncanclark.domain.model.params

import kotlinx.serialization.SerialName

data class ParamsNearbyPlace(
    val fieldMasks: String,
    val bodyParams: NearbyPlaceRequestParams,
)

data class NearbyPlaceRequestParams(
    @SerialName("locationRestriction") val locationRestriction: LocationRestriction,
    @SerialName("includedTypes") val includedTypes: String,
    @SerialName("maxResultCount") val maxResultCount: Int = 20, // Max number is between 1 and 20
)

data class LocationRestriction(
    @SerialName("circle") val circle: Circle,
)

data class Circle (
    @SerialName("center") val center: Center,
    @SerialName("radius") val radius: Double = 500.0,
)

data class Center(
    @SerialName("latitude") val latitude: Double,
    @SerialName("latitude") val longitude: Double,
)