@file:Suppress("DEPRECATION")

package com.example.duncanclark.ui_feature_search_nearby_places.composable.content

import android.app.Activity
import android.location.Geocoder
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.duncanclark.domain.model.ui.Places
import com.example.duncanclark.ui_feature_search_nearby_places.view_model.SearchNearbyPlacesViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import java.io.IOException

@Composable
fun MapContent(
    modifier: Modifier,
    queryResult: Places,
    activity: Activity,
    viewModel: SearchNearbyPlacesViewModel = hiltViewModel()
) {
    val defaultLatLng = LatLng(39.75585774171548, -104.99407350612972)
    var cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(defaultLatLng, 16f)
    }
    val placesWithLatLng by viewModel.placesWithLatLng.observeAsState()


    var mapHasLoaded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.fetchLatLngForPlaces(activity, queryResult)
    }
    when {
        (placesWithLatLng != null) && (placesWithLatLng!!.isNotEmpty()) -> {
            cameraPositionState.position = CameraPosition.fromLatLngZoom(
                placesWithLatLng!!.first().second,
                16f
            )
        }
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        onMapLoaded = { mapHasLoaded = true }
    ) {
        placesWithLatLng?.forEach { (place, latLng) ->
            Marker(
                state = rememberMarkerState(position = latLng),
                title = place.displayName
            )
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
                text = "Loading",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSecondary,
            )
        }
    }
}

fun getLatLngFromAddress(activity: Activity, strAddress: String): LatLng? {
    val geocoder = Geocoder(activity)
    return try {
        val addressList = geocoder.getFromLocationName(strAddress, 1)
        if (!addressList.isNullOrEmpty()) {
            val address = addressList[0]
            LatLng(address.latitude, address.longitude)
        } else {
            null
        }
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}
