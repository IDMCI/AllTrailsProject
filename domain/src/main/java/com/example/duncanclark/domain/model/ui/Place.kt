package com.example.duncanclark.domain.model.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

typealias Places = List<Place>
typealias LunchPlaces = List<Place.LunchPlace>
typealias PlaceId = String

@Parcelize
sealed class Place: BasePlace, Parcelable {
    @Parcelize
    data class LunchPlace(
        override val placeId: PlaceId,
        override val displayName: String,
        val rating: Double?,
        val servesLunch: Boolean?,
        override val languageCode: String?,
        override val isSelected: Boolean = false,
    ) : Place()
}

internal interface BasePlace {
    val placeId: PlaceId
    val displayName: String
    val languageCode: String?
    val isSelected: Boolean
}