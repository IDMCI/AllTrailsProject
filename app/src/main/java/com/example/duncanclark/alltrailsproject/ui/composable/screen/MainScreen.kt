package com.example.duncanclark.alltrailsproject.ui.composable.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.duncanclark.alltrailsproject.ui.composable.component.SearchBar
import com.example.duncanclark.alltrailsproject.ui.composable.screen.nav_host.MainActivityNavHost

@Composable
fun MainScreen(
    modifier: Modifier
) {
    val navController = rememberNavController()

    Box(modifier) {
        MainActivityNavHost(
            Modifier.padding(top = 96.dp),
            navController
        )
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
        ) { query ->
            navController.navigate("search/$query")
        }
    }
}