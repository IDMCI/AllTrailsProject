package com.example.duncanclark.alltrailsproject.ui.composable.screen

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.duncanclark.alltrailsproject.ui.composable.component.SearchBar
import com.example.duncanclark.alltrailsproject.ui.composable.screen.nav_host.MainNavHost

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    activity: Activity,
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Box(modifier.padding(innerPadding)) {
            MainNavHost(
                modifier = Modifier.padding(top = 84.dp)
                    .background(MaterialTheme.colorScheme.secondary),
                activity = activity,
                navController = navController
            )
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
            ) { query ->
                navController.navigate("search/$query")
            }
        }
    }
}