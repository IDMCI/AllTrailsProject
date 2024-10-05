package com.example.duncanclark.alltrailsproject.ui.composable.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.duncanclark.alltrailsproject.ui.composable.screen.nav_host.MainActivityNavHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    var currentQuery by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("AllTrails at Lunch") }
            )
        },
        floatingActionButton = {
            if (currentQuery.isNotEmpty()) {
                MapsToSearchFloatingActionButton(
                    modifier = Modifier,
                    currentScreen = "search",
                    query = currentQuery
                ) {
                    navController.navigate("maps/$currentQuery")
                }
            }
        }
    ) { innerPadding ->
        Box(modifier.padding(innerPadding)) {
            MainActivityNavHost(
                Modifier.padding(top = 96.dp),
                navController
            )
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
            ) { query ->
                currentQuery = query
                navController.navigate("search/$query")
            }
        }
    }
}