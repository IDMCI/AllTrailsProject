package com.example.duncanclark.ui_feature_search_nearby_places.composable.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.duncanclark.common.ui.state.UiState
import com.example.duncanclark.domain.model.ui.Place
import com.example.duncanclark.ui_feature_search_nearby_places.composable.component.SearchNearbyPlacesResults
import com.example.duncanclark.ui_feature_search_nearby_places.view_model.SearchNearbyPlacesViewModel
import com.google.android.gms.maps.model.LatLng

@Composable
fun SearchNearbyPlacesScreen(
    modifier: Modifier,
    query: String? = null,
    lat: Double? = null,
    lng: Double? = null,
    navHostController: NavHostController = rememberNavController(),
    viewModel: SearchNearbyPlacesViewModel = hiltViewModel(),
) {

    // States & Statuses
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var statusMessage by remember { mutableStateOf("") }
    var queryResult by remember { mutableStateOf(emptyList<Place>()) }

    LaunchedEffect(
        query,
        lat,
        lng
    ) {
        when {
            query != null -> viewModel.searchByText(query)
            (lat != null) && (lng != null) -> viewModel.searchByLocation(lat, lng)
            else -> return@LaunchedEffect
        }
    }

    when (uiState) {
        is UiState.Error -> {
            (uiState as? UiState.Error)?.let {
                queryResult = emptyList()
                statusMessage = it.message
            }
        }
        UiState.Idle -> {
            queryResult = emptyList()
            statusMessage = ""
        }
        UiState.Loading -> {
            (uiState as? UiState.Loading)?.let {
                queryResult = emptyList()
                statusMessage = "Loading..."
            }
        }
        is UiState.Success -> {
            (uiState as? UiState.Success)?.let { state ->
                queryResult = state.data
                statusMessage = when (state.data.isEmpty()) {
                    true -> "No search results"
                    false -> ""
                }
                SearchNearbyPlacesResults(
                    modifier = modifier,
                    statusMessage = statusMessage,
                    queryResult = state.data,
                ) { placeId ->
                    navHostController.navigate("map-nearby-places")
                }
            }
        }
    }
}