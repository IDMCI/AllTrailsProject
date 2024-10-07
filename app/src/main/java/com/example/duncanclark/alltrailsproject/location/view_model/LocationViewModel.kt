package com.example.duncanclark.alltrailsproject.location.view_model

import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import com.example.duncanclark.alltrailsproject.location.handler.PermissionHandler
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@HiltViewModel
class LocationViewModel @Inject constructor(
    private val permissionHandler: PermissionHandler,
    private val fusedLocationClient: FusedLocationProviderClient,
    private val application: Application,
) : AndroidViewModel(application) {

    private val _location = MutableStateFlow<Location?>(null)
    val location: StateFlow<Location?> get() = _location

    fun requestPermission(activity: Activity) {
        permissionHandler.requestPermission(
            activity,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) { isGranted ->
            if (isGranted && ContextCompat.checkSelfPermission(
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
    }
}