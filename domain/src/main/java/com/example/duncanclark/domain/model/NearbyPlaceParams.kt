package com.example.duncanclark.domain.model

data class NearbyPlaceParams(
    // TODO Add Google Maps SDK (NOT the Google Places SDK)
//    val location: LatLng,
    val includedTypes: IncludedTypes,
    val maxResultCount: Int = 10, // Default value
)