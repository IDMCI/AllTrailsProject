package com.example.duncanclark.ui_feature_search_nearby_places.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.duncanclark.domain.model.ui.Place
import com.example.duncanclark.domain.model.ui.Places
import com.example.duncanclark.ui_feature_search_nearby_places.model.MapMarker
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapWithNearbyPlacesViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
): ViewModel() {
    private val _places = mutableListOf<Place>()
    val mapMarkers = mutableListOf<MapMarker>()
    var selectedMapMarker: MapMarker? = null

    init {
//        val savedPlaces: Places? = savedStateHandle["places"]
//        savedPlaces?.let { setPlaces(it) }
    }

    fun setPlaces(places: Places) {
        _places.addAll(places)
    }
}