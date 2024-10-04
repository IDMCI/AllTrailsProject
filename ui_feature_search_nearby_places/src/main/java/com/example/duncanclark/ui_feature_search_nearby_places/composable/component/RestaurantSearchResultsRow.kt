package com.example.duncanclark.ui_feature_search_nearby_places.composable.component

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.duncanclark.domain.model.ui.Place
import com.example.duncanclark.domain.model.ui.PlaceId
import com.example.duncanclark.ui_feature_search_nearby_places.R

@Composable
fun RestaurantSearchResultsRow(
    modifier: Modifier,
    place: Place,
    clickable: Boolean,
    onClick: (PlaceId) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp)
            .clickable(clickable) {
                onClick(place.placeId)
            }
    ) {
        Column {
            // Display name
            Text(
                modifier = Modifier.weight(1F),
                text = place.displayName,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            // Ratings & Reviews
            Row(
                modifier = Modifier.semantics(mergeDescendants = true) {}
            ) {
                Icon(
                    modifier = Modifier.scale(1f),
                    bitmap = ImageBitmap.imageResource(R.drawable.icon_star_material_design_3),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "{ratings}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    modifier = Modifier.padding(horizontal = 2.dp),
                    text = "\u2022",
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
                text = "{ supporting text }",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Light,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewRestaurantSearchResultsRow() {
    val place = Place.LunchPlace(
        placeId = "",
        displayName = "My Restaurant",
        rating = 5.0,
        servesLunch = true,
        languageCode = "en",
        isSelected = false
    )
    RestaurantSearchResultsRow(
        Modifier.width(200.dp),
        place,
        false
    ) {}
}
