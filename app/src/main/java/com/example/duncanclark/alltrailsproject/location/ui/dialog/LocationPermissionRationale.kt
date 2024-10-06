package com.example.duncanclark.alltrailsproject.location.ui.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

// TODO DC: Improve pattern or remove

@Composable
fun LocationPermissionRationale(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onOkay: () -> Unit,
)  {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Location Permission Required")
        },
        text = {
            Text(
                "This app needs access to your coarse location" +
                        " to provide location-based features."
            )
        },
        confirmButton = {
            Button(onClick = onOkay) {
                Text("Grant Permission")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}