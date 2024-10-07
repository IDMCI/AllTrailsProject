package com.example.duncanclark.domain.model.route

import android.os.Parcelable
import com.example.duncanclark.domain.model.ui.PlaceId
import com.example.duncanclark.domain.model.ui.Places
import kotlinx.parcelize.Parcelize

@Parcelize
data class RouteMapNearbyPlaces(
    val searchResult: SearchResult
): Parcelable

@Parcelize
data class SearchResult(
    val places: Places = emptyList(),
//    val selected: PlaceId = "",
): Parcelable

//@Serializable
//data class LunchSearchResults(
//    val placeId: PlaceId,
//    val displayName: String,
//    val rating: Double?,
//    val servesLunch: Boolean?,
//    val languageCode: String?,
//    val isSelected: Boolean = false,
//)

val originalRoute = "map-nearby-places/{searchResults}"