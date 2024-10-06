package com.example.duncanclark.alltrailsproject.location.view_model

import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.duncanclark.alltrailsproject.location.handler.PermissionHandler
import com.example.duncanclark.alltrailsproject.location.service.LocationService
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

// TODO DC: Improve pattern or remove

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@HiltViewModel
class LocationViewModel @Inject constructor(
//    private val fusedLocationClient: FusedLocationProviderClient,
    private val permissionHandler: PermissionHandler,
    private val fusedLocationClient: FusedLocationProviderClient,
    private val application: Application,
) : AndroidViewModel(application) {

    private val _permissionGranted = MutableStateFlow(false)
    val permissionGranted: StateFlow<Boolean> get() = _permissionGranted

    private val _showPermissionDialog = MutableStateFlow(true)
    val showPermissionDialog: StateFlow<Boolean> get() = _showPermissionDialog

    private val _location = MutableStateFlow<Location?>(null)
    val location: StateFlow<Location?> get() = _location

    fun getLocation(activity: Activity) {
        if (ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                 location?.let {
                     _location.value = it
                 }
            }
        }
    }

    fun requestPermission(activity: Activity) {
        permissionHandler.requestPermission(
            activity,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) { isGranted ->
            _permissionGranted.value = isGranted
            _showPermissionDialog.value = false
            getLocation(activity)
        }
    }

    fun closePermissionDialog() {
        _showPermissionDialog.value = false
    }
}