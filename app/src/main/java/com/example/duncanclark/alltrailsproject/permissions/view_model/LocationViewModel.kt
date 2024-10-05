package com.example.duncanclark.alltrailsproject.permissions.view_model

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor() : ViewModel() {
    private val _permissionRequestState = MutableStateFlow(false)
    val permissionRequestState: StateFlow<Boolean> = _permissionRequestState

    private val _permissionGranted = MutableStateFlow(false)
    val permissionGranted: StateFlow<Boolean> = _permissionGranted

    fun requestLocationPermission() {
        _permissionRequestState.value = true
    }

    fun onPermissionResult(isGranted: Boolean) {
        _permissionGranted.value = isGranted
        _permissionRequestState.value = false
    }

    fun resetPermissionRequestState() {
        _permissionRequestState.value = false
    }
}