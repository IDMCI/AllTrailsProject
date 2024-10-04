package com.example.duncanclark.alltrailsproject.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.duncanclark.ui_feature_search_nearby_places.composable.screen.SearchNearbyPlacesScreen

@Composable
fun MainActivityNavHost(
    modifier: Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "search-nearby-places/list"
    ) {
        composable("search-nearby-places/list") {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.tertiary)
            ) {
                SearchNearbyPlacesScreen(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController
                )
            }
        }
        composable(
            "search-nearby-places/map/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("id")
            Box(
                modifier = modifier
                    .fillMaxSize()
            ) {
                Text("Not implemented yet")
            }
        }
    }
}