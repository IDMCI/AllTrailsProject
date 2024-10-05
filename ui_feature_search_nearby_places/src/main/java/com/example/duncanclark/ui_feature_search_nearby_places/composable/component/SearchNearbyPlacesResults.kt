package com.example.duncanclark.ui_feature_search_nearby_places.composable.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.duncanclark.domain.model.ui.Place
import com.example.duncanclark.domain.model.ui.PlaceId

@Composable
fun SearchNearbyPlacesResults(
    modifier: Modifier,
    statusMessage: String,
    queryResult: List<Place>,
    onClick: (PlaceId) -> Unit
) {
    when(statusMessage.isEmpty()) {
        // Display results
        true -> {
            LazySearchResultsColumn(
                modifier = modifier,
                places = queryResult,

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