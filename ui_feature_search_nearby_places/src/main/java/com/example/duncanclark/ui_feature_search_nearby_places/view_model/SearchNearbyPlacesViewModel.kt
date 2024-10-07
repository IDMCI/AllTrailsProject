package com.example.duncanclark.ui_feature_search_nearby_places.view_model

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duncanclark.common.ui.state.UiState
import com.example.duncanclark.domain.model.ui.Place
import com.example.duncanclark.domain.model.ui.Places
import com.example.duncanclark.domain.usecase.GetNearbyPlacesLunchUseCaseImpl
import com.example.duncanclark.domain.usecase.SearchTextLunchUseCaseImpl
import com.example.duncanclark.ui_feature_search_nearby_places.composable.content.getLatLngFromAddress
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberMarkerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class SearchNearbyPlacesViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    @Named("GetNearbyPlacesLunchUseCaseImpl")
    private val getNearbyPlacesUseCase: GetNearbyPlacesLunchUseCaseImpl,
    @Named("SearchTextLunchUseCaseImpl")
    private val searchTextUseCase: SearchTextLunchUseCaseImpl,
): ViewModel() {
    private val _uiState = MutableStateFlow<UiState<Places>>(UiState.Idle)
    val uiState: StateFlow<UiState<Places>> = _uiState
    private val _placesWithLatLng = MutableLiveData<List<Pair<Place, LatLng>>>()
    val placesWithLatLng: LiveData<List<Pair<Place, LatLng>>> get() = _placesWithLatLng


    private fun loading() {
        _uiState.value = UiState.Loading
    }

    init {
        // There is not a "NavType.Double" to check for in navArguments.
        val lat: Float? = savedStateHandle["lat"]
        val lng: Float? = savedStateHandle["lng"]

        if((lat != null) && (lng != null)) {
            searchByLocation(lat.toDouble(), lng.toDouble())
        }
    }

    private fun searchByLocation(
        lat: Double,
        lng: Double,
    ) {
        viewModelScope.launch {
            loading()
            getNearbyPlacesUseCase.execute(lat, lng).collect { result ->
                _uiState.value = when {
                    result.isSuccess -> {
                        UiState.Success(result.getOrDefault(emptyList()))
                    }
                    result.isFailure -> {
                        UiState.Error("Oh no! Something went wrong!")
                    }
                    else -> UiState.Error("Oh no! Something went REALLY wrong!")
                }
            }
        }
    }

    fun searchByText(textQuery: String) {
        viewModelScope.launch {
            loading()
            searchTextUseCase.execute(textQuery).collect { result ->
                _uiState.value = when {
                    result.isSuccess -> {
                        UiState.Success(result.getOrDefault(emptyList()))
                    }
                    result.isFailure -> {
                        UiState.Error("Oh no! Something went wrong!")
                    }
                    else -> UiState.Error("Oh no! Something went REALLY wrong!")
                }
            }
        }
    }

    fun fetchLatLngForPlaces(
        activity: Activity,
        places: Places
    ) {
        viewModelScope.launch {
            val placesLatLng = places.mapNotNull { place ->
                when (place) {
                    is Place.LunchPlace -> {
                        val latLng = getLatLngFromAddress(activity, place.formattedAddress)
                        if (latLng != null) {
                            place to latLng
                        } else null
                    }
                }
            }
            _placesWithLatLng.postValue(placesLatLng)
        }
    }
}