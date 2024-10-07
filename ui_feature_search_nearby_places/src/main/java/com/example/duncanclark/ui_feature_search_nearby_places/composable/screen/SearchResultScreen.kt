package com.example.duncanclark.ui_feature_search_nearby_places.composable.screen

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.duncanclark.domain.model.ui.Place
import com.example.duncanclark.ui_feature_search_nearby_places.composable.content.SearchNearbyPlacesResults
import com.example.duncanclark.ui_feature_search_nearby_places.view_model.SearchNearbyPlacesViewModel

@Composable
fun SearchResultScreen(
    modifier: Modifier,
    query: String? = null,
    lat: Double? = null,
    lng: Double? = null,
    activity: Activity,
    viewModel: SearchNearbyPlacesViewModel = hiltViewModel(),
    ) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var statusMessage by remember { mutableStateOf("") }
    var queryResult by remember { mutableStateOf(emptyList<Place>()) }
    var showFab by remember { mutableStateOf(true) }

    LaunchedEffect(
        query,
        lat,
        lng
    ) {
        when {
            query != null -> viewModel.searchByText(query)
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
                    queryResult = state.data,
                    activity = activity,
                )
            }
        }
    }
    if (statusMessage.isEmpty() && queryResult.isNotEmpty()) {
        Column(
            modifier = modifier
                .padding()
                .fillMaxSize(),
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