package com.example.duncanclark.alltrailsproject.ui.composable.screen.nav_host

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
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
import com.example.duncanclark.alltrailsproject.location.ui.screen.LocationScreen
import com.example.duncanclark.ui_feature_search_nearby_places.composable.screen.SearchResultScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MainNavHost(
    modifier: Modifier,
    navController: NavHostController,
    activity: Activity,
) {
    // TODO DC: Remove this logic here and cache data in Room instead.
    var queryHistory by remember { mutableStateOf<String?>(null) }
    var latHistory by remember { mutableStateOf<Double?>(null) }
    var lngHistory by remember { mutableStateOf<Double?>(null) }

    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = "current-location",
    ) {
        composable("current-location") {
            LocationScreen(
                activity = activity,
                navController = navController
            )
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
            SearchResultScreen(
                modifier = Modifier.fillMaxSize(),
                query = queryHistory,
            )
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
            SearchResultScreen(
                modifier = Modifier.fillMaxSize(),
                lat = latHistory,
                lng = lngHistory,
            )
        }
    }
}