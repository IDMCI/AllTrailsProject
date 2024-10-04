package com.example.duncanclark.ui_feature_search_nearby_places.composable.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.duncanclark.common.ui.state.UiState
import com.example.duncanclark.ui_feature_search_nearby_places.view_model.SearchNearbyPlacesViewModel

@Composable
fun SearchBar(
    modifier: Modifier,
    viewModel: SearchNearbyPlacesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var showStatusMessage by remember { mutableStateOf(false) }
    var statusMessage by remember { mutableStateOf("") }

    when (uiState) {
        is UiState.Error -> {
            showStatusMessage = true
            (uiState as? UiState.Error)?.let {
                statusMessage = it.message
            }
        }
        UiState.Idle -> {
            showStatusMessage = false
            statusMessage = ""
        }
        UiState.Loading -> {
            showStatusMessage = true
            (uiState as? UiState.Loading)?.let {
                statusMessage = "Loading..."
            }
        }
        is UiState.Success -> {
            showStatusMessage = false
            (uiState as? UiState.Success)?.let { state ->
                LazySearchResultsColumn(
                    modifier = Modifier.fillMaxSize(),
                    places = state.data
                )
            }
        }
    }
}