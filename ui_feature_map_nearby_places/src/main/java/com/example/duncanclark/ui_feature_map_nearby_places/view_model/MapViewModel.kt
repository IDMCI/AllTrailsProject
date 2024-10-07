package com.example.duncanclark.ui_feature_map_nearby_places.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.duncanclark.common.ui.state.UiState
import com.example.duncanclark.domain.model.ui.Place
import com.example.duncanclark.domain.model.ui.Places
import com.example.duncanclark.domain.usecase.GetNearbyPlacesLunchUseCase
import com.example.duncanclark.domain.usecase.SearchTextLunchUseCase
import com.example.duncanclark.ui_feature_map_nearby_places.model.MapMarker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
): ViewModel() {
    private val _places = mutableListOf<Place>()
    val mapMarkers = mutableListOf<MapMarker>()
    var selectedMapMarker: MapMarker? = null

    fun setPlaces(places: Places) {
        _places.addAll(places)
    }

    private val _uiState = MutableStateFlow<UiState<Places>>(UiState.Idle)
    val uiState: StateFlow<UiState<Places>> = _uiState

    private fun loading() {
        _uiState.value = UiState.Loading
    }

//    init {
//        // There is not a "NavType.Double" to check for in navArguments.
//        val lat: Float? = savedStateHandle["lat"]
//        val lng: Float? = savedStateHandle["lng"]
//
//        if((lat != null) && (lng != null)) {
//            searchByLocation(lat.toDouble(), lng.toDouble())
//        }
//    }
//
//    private fun searchByLocation(
//        lat: Double,
//        lng: Double,
//    ) {
//        viewModelScope.launch {
//            loading()
//            getNearbyPlacesLunchUseCase.execute(lat, lng).collect { result ->
//                _uiState.value = when {
//                    result.isSuccess -> {
//                        UiState.Success(result.getOrDefault(emptyList()))
//                    }
//                    result.isFailure -> {
//                        UiState.Error("Oh no! Something went wrong!")
//                    }
//                    else -> UiState.Error("Oh no! Something went REALLY wrong!")
//                }
//            }
//        }
//    }
//
//    private fun searchByText(textQuery: String) {
//        viewModelScope.launch {
//            loading()
//            searchTextLunchUseCase.execute(textQuery).collect { result ->
//                _uiState.value = when {
//                    result.isSuccess -> {
//                        UiState.Success(result.getOrDefault(emptyList()))
//                    }
//                    result.isFailure -> {
//                        UiState.Error("Oh no! Something went wrong!")
//                    }
//                    else -> UiState.Error("Oh no! Something went REALLY wrong!")
//                }
//            }
//        }
//    }
}