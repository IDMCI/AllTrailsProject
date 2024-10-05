package com.example.duncanclark.ui_feature_map_nearby_places.composable

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.duncanclark.ui_feature_map_nearby_places.R
import com.example.duncanclark.ui_feature_map_nearby_places.view_model.MapWithNearbyPlacesViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.AdvancedMarker
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MapWithNearbyPlacesScreen(
    modifier: Modifier,
    viewModel: MapWithNearbyPlacesViewModel = hiltViewModel(),
    navController: NavController = rememberNavController(),
) {
    val home = LatLng(40.48476274565125, -104.93374282290922)
    val markerState = rememberMarkerState(position = home)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(home, 16f)
    }
    var mapHasLoaded by remember { mutableStateOf(false) }

    val other = LatLng(40.48514568460747, -104.92020959933022)
    val other2 = LatLng(40.477409485867724, -104.9418818468041)

    val myList = listOf(other, other2, home)

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,
        onMapLoaded = { mapHasLoaded = true }
    ) {
        myList.forEach {
            if (it == home) {
                MarkerComposable(state = markerState) {
                    Text(text = "Hello")
                }
            }
            else {
                AdvancedMarker(
                    state = MarkerState(position = it),
                    title = "others",
                )
            }
        }
    }
    if(!mapHasLoaded) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.secondary),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(12.dp),
                text = stringResource(R.string.ui_map_loading),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSecondary,
            )
        }
    }
}