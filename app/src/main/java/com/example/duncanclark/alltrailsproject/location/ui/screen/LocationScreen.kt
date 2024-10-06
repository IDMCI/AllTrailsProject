package com.example.duncanclark.alltrailsproject.location.ui.screen

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.duncanclark.alltrailsproject.location.ui.dialog.LocationPermissionRationale
import com.example.duncanclark.alltrailsproject.location.view_model.LocationViewModel

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun LocationScreen(
    navController: NavHostController,
    activity: Activity,
    viewModel: LocationViewModel = hiltViewModel()
) {
    val showDialog by viewModel.showPermissionDialog.collectAsStateWithLifecycle()
    val permissionGranted by viewModel.permissionGranted.collectAsStateWithLifecycle()
    val location by viewModel.location.collectAsStateWithLifecycle()

    LaunchedEffect(location) {
        location?.let {
            val lat: Float = it.latitude.toFloat()
            val lng: Float = it.longitude.toFloat()
            navController.navigate("search-loc/${lat}/${lng}")
        }
    }

    if (showDialog && !permissionGranted) {
        LocationPermissionRationale(
            onDismiss = { viewModel.closePermissionDialog() },
            onOkay = {
                viewModel.closePermissionDialog()
                viewModel.requestPermission(activity)
            }
        )
    }
}