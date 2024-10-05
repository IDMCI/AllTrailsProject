package com.example.duncanclark.alltrailsproject.permissions.service

import android.Manifest
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.IBinder
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LocationService : Service() {

    @Inject lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(startIntent: Intent?, flags: Int, startId: Int): Int {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            try {
                fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                    location?.let {
                        val broadcastIntent = Intent("LocationUpdate")
                        broadcastIntent.putExtra("latitude", it.latitude)
                        broadcastIntent.putExtra("longitude", it.longitude)
                        sendBroadcast(broadcastIntent)
                    }
                    stopSelf()
                }
            } catch (e: SecurityException) {
                // Handle the exception
                stopSelf()
                Log.e("LocationService", "SecurityException: ${e.message}")
            }
        } else {
            // Handle the case where permission is not granted
            stopSelf()
        }
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}