package com.example.duncanclark.ui_feature_search_nearby_places.composable.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.duncanclark.domain.model.ui.Place

@Composable
fun RestaurantSearchResultsRow(
    modifier: Modifier,
    place: Place.LunchPlace,
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = place.placeId,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            // Display name
            Text(
                text = place.displayName,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            // Ratings & Reviews
            Row(
                modifier = Modifier.semantics(mergeDescendants = true) {}
            ) {
                // TODO DC: Add icon
//                Icon(
//                    modifier = Modifier.scale(1f),
//                    bitmap = ImageBitmap.imageResource(),
//                    contentDescription = "",
//                    tint = MaterialTheme.colorScheme.primary
//                )
                Text(
                    text = "{ratings} • ",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Text(
                    modifier = Modifier.padding(horizontal = 2.dp),
                    text = "(reviews)",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Light,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            Text(
                modifier = Modifier.padding(horizontal = 2.dp),
                text = "{supporting text}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Light,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}