package com.example.duncanclark.ui_feature_search_nearby_places.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duncanclark.common.ui.state.UiState
import com.example.duncanclark.domain.model.ui.LunchPlaces
import com.example.duncanclark.domain.model.ui.Places
import com.example.duncanclark.domain.usecase.GetNearbyPlacesLunchUseCase
import com.example.duncanclark.domain.usecase.GetNearbyPlacesLunchUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class SearchNearbyPlacesViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    @Named("GetNearbyPlacesLunchUseCaseImpl") private val useCase: GetNearbyPlacesLunchUseCaseImpl,
): ViewModel() {
    private val _uiState = MutableStateFlow<UiState<Places>>(UiState.Idle)
    val uiState: StateFlow<UiState<Places>> = _uiState

    private fun loading() {
        _uiState.value = UiState.Loading
    }

    init {
        val savedState = savedStateHandle.get<UiState<LunchPlaces>>("search_nearby_places")
        savedState?.let { _uiState.value = it }

        // TODO DC: Add permission logic to get current location
        val lat = 40.479822043320816
        val lng = -104.89954079298904
        searchByLocation(lat, lng)
    }

    fun searchByLocation(
        lat: Double,
        lng: Double
    ) {
        viewModelScope.launch {
            loading()
            useCase.execute(lat, lng).collect { result ->
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
}