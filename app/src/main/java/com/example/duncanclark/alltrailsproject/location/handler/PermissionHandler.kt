package com.example.duncanclark.alltrailsproject.location.handler

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import javax.inject.Inject
import javax.inject.Singleton

// TODO DC: Need to fix the Activity injection otherwise remove
//
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

        fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            if (requestCode == REQUEST_CODE) {
                val granted = grantResults.isNotEmpty() &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED
                permissionsCallback?.invoke(granted)
                permissionsCallback = null
            }
        }
    }
}