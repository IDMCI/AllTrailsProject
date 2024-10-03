package com.example.duncanclark.domain.model.params

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class ParamsNearbyPlace(
    val fieldMasks: String,
    val bodyParams: NearbyPlaceRequestParams,
)

@Parcelize
data class NearbyPlaceRequestParams(
    val locationRestriction: LocationRestriction,
    val includedTypes: String,
    val maxResultCount: Int = 10,
): Parcelable

@Parcelize
data class LocationRestriction(
    val circle: Circle,
): Parcelable

@Parcelize
data class Circle (
    val center: Center,
    val radius: Double = 500.0,
): Parcelable

@Parcelize
data class Center(
    val latitude: Double,
    val longitude: Double,
): Parcelable