package com.example.duncanclark.ui_feature_search_nearby_places.composable.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.duncanclark.domain.model.ui.Place
import com.example.duncanclark.domain.model.ui.PlaceId

@Composable
fun SearchNearbyPlacesResults(
    modifier: Modifier,
    queryResult: List<Place>,
    onClick: (PlaceId) -> Unit
) {
    LazySearchResultsColumn(
        modifier = modifier,
        places = queryResult,
    ) { placeId ->
        onClick(placeId)
    }
}