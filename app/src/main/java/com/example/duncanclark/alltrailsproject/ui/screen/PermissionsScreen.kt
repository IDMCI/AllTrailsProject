package com.example.duncanclark.alltrailsproject.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.duncanclark.alltrailsproject.permissions.handler.PermissionsHandler
import com.example.duncanclark.alltrailsproject.permissions.view_model.LocationViewModel


@Composable
fun PermissionsScreen(
    viewModel: LocationViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    PermissionsHandler(viewModel = viewModel)
    val permissionGranted by viewModel.permissionGranted.collectAsState()

    // Observe the permission result and navigate accordingly
    LaunchedEffect(permissionGranted) {
        if (permissionGranted) {
            navController.navigate("search/") {
                popUpTo("permissions") { inclusive = true }
            }
        } else {
            // Handle the case where the permission is denied
            navController.navigate("denied") {
                popUpTo("permissions") { inclusive = true }
            }
        }
    }

    // UI content for permissions screen
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Requesting Location Permission...")
    }
}