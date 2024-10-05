package com.example.duncanclark.ui_feature_search_nearby_places.composable.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.duncanclark.domain.model.ui.Place
import com.example.duncanclark.domain.model.ui.PlaceId

@Composable
fun LazySearchResultsColumn(
    modifier: Modifier,
    places: List<Place>,
    onClick: (PlaceId) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(places) { place ->
            when (place) {
                is Place.LunchPlace -> {
                    RestaurantSearchResultsRow(
                        modifier = Modifier
                            .padding(horizontal = 18.dp, vertical = 12.dp)
                            .clickable {
                                onClick(place.placeId)
                            },
                        place = place
                    )
                }
            }
        }
    }
}