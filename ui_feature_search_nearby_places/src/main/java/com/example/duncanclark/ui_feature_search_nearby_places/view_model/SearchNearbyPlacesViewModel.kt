package com.example.duncanclark.ui_feature_search_nearby_places.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.duncanclark.domain.usecase.GetNearbyPlacesLunchUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchNearbyPlacesViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val useCase: GetNearbyPlacesLunchUseCaseImpl,
): ViewModel() {
}