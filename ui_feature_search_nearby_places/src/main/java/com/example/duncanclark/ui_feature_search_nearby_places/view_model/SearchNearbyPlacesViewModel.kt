package com.example.duncanclark.ui_feature_search_nearby_places.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duncanclark.common.ui.state.UiState
import com.example.duncanclark.domain.model.ui.LunchPlaces
import com.example.duncanclark.domain.usecase.SearchNearbyPlacesLunchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchNearbyPlacesViewModel {
}