package com.example.duncanclark.alltrailsproject.ui.composable.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.duncanclark.domain.model.route.FabState

@Composable
fun MapsToSearchFloatingActionButton(
    modifier: Modifier,
    fabState: FabState?,
    onFabClick: () -> Unit
) {
    fabState?.let {
        FloatingActionButton(
            modifier = modifier.padding(12.dp),
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
            onClick = onFabClick
        ) {
            Text(text = fabState.displayName)
        }
    }
}