package com.example.duncanclark.ui_feature_search_nearby_places.composable.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.duncanclark.domain.model.ui.Place

@Composable
fun LazySearchResultsColumn(
    modifier: Modifier,
    places: List<Place>,
) {
    var selectedPlaceId by remember { mutableStateOf("") }

    LazyColumn(
        modifier = modifier
    ) {
        items(places) { place ->
            when (place) {
                is Place.LunchPlace -> {
                    RestaurantSearchResultsRow(
                        modifier = Modifier
                            .padding(horizontal = 18.dp, vertical = 12.dp),
                        place = place,
                        isSelected = (place.placeId == selectedPlaceId),
                        onClick = {
                            selectedPlaceId = when (selectedPlaceId == it) {
                                true -> ""
                                false -> place.placeId
                            }
                        }
                    )
                }
            }
        }
    }
}