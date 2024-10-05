package com.example.duncanclark.alltrailsproject.ui.composable.screen.nav_host

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.duncanclark.ui_feature_search_nearby_places.composable.screen.SearchNearbyPlacesScreen

@Composable
fun MainActivityNavHost(
    modifier: Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "home",
    ) {
        composable("home") {
            SearchNearbyPlacesScreen(
                modifier = Modifier.fillMaxSize(),
            )
        }
        composable(
            "search/{query}",
            listOf(navArgument("query") { type = NavType.StringType })
        ) { navBackStackEntry ->
            val query = navBackStackEntry.arguments?.getString("query") ?: ""
            SearchNearbyPlacesScreen(
                modifier = Modifier.fillMaxSize(),
                query = query
            )
        }
        composable(
            "search-loc/{lat}/{lng}",
            listOf(
                navArgument("lat") { type = NavType.FloatType },
                navArgument("lng") { type = NavType.FloatType },
            ),
        ) { navBackStackEntry ->
            val lat = navBackStackEntry.arguments?.getFloat("lat")
            val lng = navBackStackEntry.arguments?.getFloat("lng")
            SearchNearbyPlacesScreen(
                modifier = Modifier.fillMaxSize(),
                lat = lat?.toDouble() ?: 0.0,
                lng = lng?.toDouble() ?: 0.0,
            )
        }
        composable(
            "details/{placeId}",
            listOf(navArgument("placeId") { type = NavType.StringType })
        ) { navBackStackEntry ->
            val placeId = navBackStackEntry.arguments?.getString("placeId") ?: ""
            Row(
                modifier = Modifier.fillMaxHeight()
            ) {
                Text("You clicked: $placeId")
            }
        }
    }
}