package com.example.duncanclark.alltrailsproject.ui.composable.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.duncanclark.domain.model.ui.Place
import com.example.duncanclark.domain.model.ui.Places
import com.example.duncanclark.alltrailsproject.ui.composable.component.LazySearchResultsColumn
import com.example.duncanclark.alltrailsproject.ui.composable.component.SearchBar
import com.example.duncanclark.alltrailsproject.ui.view_model.SearchNearbyPlacesViewModel

@Composable
fun MainScreen(
    modifier: Modifier,
    navController: NavController = rememberNavController(),
    viewModel: SearchNearbyPlacesViewModel = hiltViewModel()
) {
    // States & Statuses
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var statusMessage by remember { mutableStateOf("") }

    // Search Results
    var query by remember { mutableStateOf("") }
    var queryResults by remember { mutableStateOf(emptyList<Place>()) }

    when (uiState) {
        is UiState.Error -> {
            (uiState as? UiState.Error)?.let {
                statusMessage = it.message
            }
        }
        UiState.Idle -> {
            statusMessage = ""
        }
        UiState.Loading -> {
            (uiState as? UiState.Loading)?.let {
                statusMessage = "Loading..."
            }
        }
        is UiState.Success -> {
            (uiState as? UiState.Success)?.let { state ->
                statusMessage = if (state.data.isEmpty()) {
                    "No search results"
                } else { "" }
                queryResults = state.data
            }
        }
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {

        SearchBar(modifier = Modifier)
        when (query.isEmpty()) {
            true -> Text("hello") // Host
            false -> {
                QueryResults(
                    modifier = Modifier.fillMaxSize(),
                    queryResults = queryResults,
                    statusMessage = statusMessage
                )
            }
        }
    }
//    if (showStatusMessage) {
//        Column(
//            modifier = modifier
//                .fillMaxSize()
//                .background(MaterialTheme.colorScheme.secondary),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Text(
//                modifier = Modifier.padding(12.dp),
//                text = statusMessage,
//                fontSize = 16.sp,
//                fontWeight = FontWeight.SemiBold,
//                fontStyle = FontStyle.Italic,
//                color = MaterialTheme.colorScheme.onSecondary,
//            )
//        }
//    }
}

@Composable
fun QueryResults(
    modifier: Modifier,
    queryResults: Places,
    statusMessage: String,
) {
    when(statusMessage.isEmpty()) {
        // Display results
        true -> {
            LazySearchResultsColumn(
                modifier = modifier,
                places = queryResults
            )
        }
        // Display status message
        false -> {
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
}