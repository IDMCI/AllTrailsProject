package com.example.duncanclark.ui_feature_map_nearby_places.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.duncanclark.ui_feature_map_nearby_places.view_model.MapWithNearbyPlacesViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.AdvancedMarker
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapWithNearbyPlacesScreen(
    modifier: Modifier,
    viewModel: MapWithNearbyPlacesViewModel = hiltViewModel(),
    navController: NavController = rememberNavController(),
) {
    val home = LatLng(40.48476274565125, -104.93374282290922)
//    val markerState = rememberMarkerState(position = home)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(home, 16f)
    }

    val other = LatLng(40.48514568460747, -104.92020959933022)
    val other2 = LatLng(40.477409485867724, -104.9418818468041)

    val myList = listOf(other, other2, home)

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState
    ) {
        myList.forEach {
            if (it == home) {
//                val pinConfig = PinConfig.builder()
//                    .setBackgroundColor(Color.MAGENTA)
//                    .build()
//                Marker(
//                    state = markerState,
//                    title = "Home",
//                    snippet = "My house in Windsor",
//                )
                MarkerComposable(
                    state = MarkerState(position = it)
                ) {
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
//        Marker(
//            state = markerState,
//            title = "Home",
//            snippet = "My house in Windsor"
//        )

    }

}