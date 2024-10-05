package com.example.duncanclark.alltrailsproject.permissions.handler

import android.Manifest
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.duncanclark.alltrailsproject.permissions.service.LocationService
import com.example.duncanclark.alltrailsproject.permissions.view_model.LocationViewModel

@Composable
fun PermissionsHandler(
    viewModel: LocationViewModel = hiltViewModel(),
) {

    val context = LocalContext.current

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        viewModel.onPermissionResult(isGranted)
        if (isGranted) {
            val intent = Intent(context, LocationService::class.java)
            context.startService(intent)
        }
    }

    // Trigger permission request based on ViewModel state
    LaunchedEffect(Unit) {
        viewModel.permissionRequestState.collect { permissionRequestState ->
            if (permissionRequestState) {
                permissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
                viewModel.resetPermissionRequestState()
            }
        }
    }
}