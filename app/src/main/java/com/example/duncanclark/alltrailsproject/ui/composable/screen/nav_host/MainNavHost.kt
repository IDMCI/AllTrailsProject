package com.example.duncanclark.alltrailsproject.ui.composable.screen.nav_host

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.duncanclark.alltrailsproject.ui.model.FabState
import com.example.duncanclark.ui_feature_map_nearby_places.composable.MapWithNearbyPlacesScreen
import com.example.duncanclark.ui_feature_search_nearby_places.composable.screen.SearchNearbyPlacesScreen

@Composable
fun MainNavHost(
    modifier: Modifier,
    navController: NavHostController,
    fabState: (FabState?) -> Unit
) {
    // TODO DC: Remove this logic here and cache data in Room instead.
    var queryHistory by remember { mutableStateOf<String?>(null) }
    var latHistory by remember { mutableStateOf<Double?>(null) }
    var lngHistory by remember { mutableStateOf<Double?>(null) }

    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = "permissions",
    ) {
        composable("permissions") {
            SearchNearbyPlacesScreen(
                modifier = Modifier,
                navHostController = navController
            )
            fabState(null)
        }
        composable("denied") {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Permission Denied. Unable to access location.",
                    color = Color.Red,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
        composable(
            "search/{query}",
            listOf(navArgument("query") { type = NavType.StringType })
        ) { navBackStackEntry ->
            queryHistory = navBackStackEntry.arguments?.getString("query") ?: ""
            SearchNearbyPlacesScreen(
                modifier = Modifier.fillMaxSize(),
                query = queryHistory,
                navHostController = navController
            )
            fabState(
                FabState(
                    displayName = "Map",
                    route = "map-nearby-places"
                )
            ).also {
                latHistory = null
                lngHistory = null
            }
        }
        composable(
            "search-loc/{lat}/{lng}",
            listOf(
                navArgument("lat") { type = NavType.FloatType },
                navArgument("lng") { type = NavType.FloatType },
            ),
        ) { navBackStackEntry ->
            latHistory = navBackStackEntry.arguments?.getFloat("lat")?.toDouble() ?: 0.0
            lngHistory = navBackStackEntry.arguments?.getFloat("lng")?.toDouble() ?: 0.0
            SearchNearbyPlacesScreen(
                modifier = Modifier.fillMaxSize(),
                lat = latHistory,
                lng = lngHistory,
                navHostController = navController
            )
            fabState(
                FabState(
                    displayName = "Map",
                    route = "map-nearby-places"
                )
            )
        }
        composable(
            "map-nearby-places",
        ) { navBackStackEntry ->
            MapWithNearbyPlacesScreen(
                modifier = Modifier.fillMaxSize(),
            )
            if (queryHistory.isNullOrEmpty()) {
                fabState(
                    FabState(
                        displayName = "List",
                        route = "search-loc/$latHistory/$lngHistory"
                    )
                )
            } else if ((latHistory == null) && (lngHistory == null)) {
                fabState(
                    FabState(
                        displayName = "List",
                        route = "search/$queryHistory"
                    )
                )
            } else {
               fabState(null)
            }
        }
    }
}