package com.example.duncanclark.ui_feature_search_nearby_places.composable.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.duncanclark.domain.model.ui.Place
import com.example.duncanclark.domain.model.ui.PlaceId

@Composable
fun RestaurantSearchResultsRow(
    modifier: Modifier,
    place: Place.LunchPlace,
    isSelected: Boolean,
    onClick: (PlaceId) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick(place.placeId)
            }
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .animateContentSize()
        ) {
            // Debugging purposes
//            Text(
//                text = place.placeId,
//                fontSize = 16.sp,
//                color = MaterialTheme.colorScheme.onPrimaryContainer
//            )
            // Display name
            Text(
                text = place.displayName,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            // Ratings & Reviews
            Text(
                text = "★ ${place.rating} • ${place.primaryType}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            if (isSelected) {
                if (place.formattedAddress.isNotEmpty()) {
                    Text(
                        modifier = Modifier.padding(horizontal = 2.dp),
                        text = "Address: ${place.formattedAddress}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                place.servesLunch?.let {
                    Text(
                        modifier = Modifier.padding(horizontal = 2.dp),
                        text = "listed as serving lunch: ${place.servesLunch}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Light,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                Text(
                    modifier = Modifier.padding(horizontal = 2.dp),
                    text = "vegetarian options: ${place.servesVegetarianFood}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Light,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    modifier = Modifier.padding(horizontal = 2.dp),
                    text = "allows dogs: ${place.allowsDogs}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Light,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}