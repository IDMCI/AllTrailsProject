package com.example.duncanclark.alltrailsproject.location.handler

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import javax.inject.Inject

class PermissionHandler @Inject constructor() {

    fun requestPermission(
        activity: Activity,
        permission: String,
        callback: (Boolean) -> Unit
    ) {
        // Checks if permission is granted
        if (ContextCompat.checkSelfPermission(activity, permission) ==
            PackageManager.PERMISSION_GRANTED) {
            callback(true)
        }
        // If not, ask for permission
        else {
            ActivityCompat.requestPermissions(activity, arrayOf(permission), REQUEST_CODE)
            permissionsCallback = callback
        }
    }

    companion object {
        private const val REQUEST_CODE = 1001
        private var permissionsCallback: ((Boolean) -> Unit)? = null
    }
}