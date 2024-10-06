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

    viewModel.location.collectAsState()

    LaunchedEffect(location) {
        location?.let {
            navController.navigate("search-loc/${it.latitude}/${it.longitude}")
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

//    Column {
//        Text("Fetching Location...")
//    }
//
//        Column {
//        Text(text = "Location Screen")
//
//        Button(onClick = { viewModel.openPermissionDialog() }) {
//            Text("Request Coarse Location Permission")
//        }
//
//        if (permissionGranted) {
//            Text(text = "Permission Granted")
//            location?.let {
//                Text(text = "Location: ${it.latitude}, ${it.longitude}")
//            }
//        } else {
//            Text(text = "Permission Not Granted")
//        }
//    }
}