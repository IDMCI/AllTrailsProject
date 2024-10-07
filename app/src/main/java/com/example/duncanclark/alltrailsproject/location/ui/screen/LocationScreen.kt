package com.example.duncanclark.alltrailsproject.location.ui.screen

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.duncanclark.alltrailsproject.location.view_model.LocationViewModel

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun LocationScreen(
    navController: NavHostController,
    activity: Activity,
    viewModel: LocationViewModel = hiltViewModel()
) {
    val location by viewModel.location.collectAsStateWithLifecycle()

    LaunchedEffect(location) {
        viewModel.requestPermission(activity)
        location?.let {
            val lat: Float = it.latitude.toFloat()
            val lng: Float = it.longitude.toFloat()
            navController.navigate("search-loc/${lat}/${lng}")
        }
    }

    if (location == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onSecondary
                ),
                onClick = {
                    viewModel.requestPermission(activity)
                }
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    text = "Refresh",
                    fontWeight = FontWeight.Bold,
                    color =  MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}