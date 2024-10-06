package com.example.duncanclark.ui_feature_search_nearby_places.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duncanclark.common.ui.state.UiState
import com.example.duncanclark.domain.model.ui.Places
import com.example.duncanclark.domain.usecase.GetNearbyPlacesLunchUseCaseImpl
import com.example.duncanclark.domain.usecase.SearchTextLunchUseCaseImpl
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
}