package com.example.duncanclark.ui_feature_search_nearby_places.composable.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.duncanclark.common.ui.state.UiState
import com.example.duncanclark.ui_feature_search_nearby_places.composable.component.LazySearchResultsColumn
import com.example.duncanclark.ui_feature_search_nearby_places.composable.component.RestaurantSearchResultsRow
import com.example.duncanclark.ui_feature_search_nearby_places.view_model.SearchNearbyPlacesViewModel

@Composable
fun SearchNearbyPlacesScreen(
    modifier: Modifier,
    navController: NavController = rememberNavController(),
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

    if (showStatusMessage) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.secondary),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(12.dp),
                text = statusMessage,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSecondary,
            )
        }
    }
}