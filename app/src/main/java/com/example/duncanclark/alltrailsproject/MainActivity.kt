package com.example.duncanclark.alltrailsproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.duncanclark.alltrailsproject.ui.theme.AllTrailsProjectTheme
import com.example.duncanclark.ui_feature_search_nearby_places.composable.screen.MapWithNearbyPlacesScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AllTrailsProjectTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text("AllTrails at Lunch") }
                        )
                    }
                ) { innerPadding ->
                    MainActivityNavHost(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainActivityNavHost(
    modifier: Modifier
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "map-nearby-places",
        modifier = modifier.fillMaxSize()
    ) {
//        composable("search-nearby-places") {
//            MainScreen(
//                modifier = Modifier.fillMaxWidth(),
//                navController = navController,
//            )
//        }
        composable("map-nearby-places") {
            MapWithNearbyPlacesScreen(
                modifier = Modifier.fillMaxWidth(),
                navController = navController,
            )
        }
    }
}