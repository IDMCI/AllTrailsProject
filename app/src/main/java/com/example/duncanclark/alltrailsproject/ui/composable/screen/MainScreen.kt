package com.example.duncanclark.alltrailsproject.ui.composable.screen

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.duncanclark.alltrailsproject.ui.composable.component.MapsToSearchFloatingActionButton
import com.example.duncanclark.alltrailsproject.ui.composable.component.SearchBar
import com.example.duncanclark.alltrailsproject.ui.composable.screen.nav_host.MainNavHost
import com.example.duncanclark.alltrailsproject.ui.model.FabState

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    activity: Activity,
) {
    val navController = rememberNavController()
    var fabState by remember { mutableStateOf<FabState?>(null) }
    var showFab by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("AllTrails @ Lunch") }
            )
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = showFab,
                enter = fadeIn(animationSpec = tween(durationMillis = 2000)),
                exit = fadeOut(animationSpec = tween(durationMillis = 2000))
            ) {
                MapsToSearchFloatingActionButton(
                    modifier = Modifier,
                    fabState = fabState
                ) {
                    fabState?.let {
                        showFab = true
                        navController.navigate(it.route)
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(modifier.padding(innerPadding)) {
            MainNavHost(
                modifier = Modifier.padding(top = 96.dp)
                    .background(MaterialTheme.colorScheme.secondary),
                activity = activity,
                navController = navController
            ) { updatedFabState ->
                showFab = (updatedFabState != null)
                fabState = updatedFabState
            }
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
            ) { query ->
                navController.navigate("search/$query")
            }
        }
    }
}