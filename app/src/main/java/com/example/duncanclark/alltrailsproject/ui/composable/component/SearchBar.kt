package com.example.duncanclark.alltrailsproject.ui.composable.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.duncanclark.ui_feature_search_nearby_places.R

@Composable
fun SearchBar(
    modifier: Modifier,
    viewModel: com.example.duncanclark.alltrailsproject.ui.view_model.SearchNearbyPlacesViewModel = hiltViewModel()
) {
    var query by remember { mutableStateOf("") }

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = query,
        onValueChange = {
            query = it
            if (query.length >= 3) {
                viewModel.searchByText(query)
            }
        },
        label = { Text(stringResource(R.string.search_bar_label)) }
    )
}