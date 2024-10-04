package com.example.duncanclark.ui_feature_search_nearby_places.composable.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.duncanclark.domain.model.ui.Place

@Composable
fun LazySearchResultsColumn(
    modifier: Modifier,
    places: List<Place>,
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 6.dp, vertical = 12.dp)
    ) {
         items(places) { place ->
            RestaurantSearchResultsRow(
                modifier = Modifier,
                place = place
            )
        }
    }
}