package com.example.duncanclark.domain.model

data class NearbyPlaceParams(
    val fieldMasks: FieldMask,
    val bodyParams: NearbyPlaceRequestParams
)

data class NearbyPlaceRequestParams(
    val locationRestriction: LocationRestriction = LocationRestriction(),
    val includedTypes: String,
    val maxResultCount: Int = 10,
)