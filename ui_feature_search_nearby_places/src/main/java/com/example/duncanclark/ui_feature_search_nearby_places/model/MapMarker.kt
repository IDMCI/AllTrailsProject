package com.example.duncanclark.ui_feature_search_nearby_places.model

import com.example.duncanclark.domain.model.ui.PlaceId

data class MapMarker(
    var placeId: PlaceId,
    var lat: Double,
    var long: Double,
    var isSelected: Boolean = false
)