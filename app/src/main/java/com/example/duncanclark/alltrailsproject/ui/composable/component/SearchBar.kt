package com.example.duncanclark.alltrailsproject.ui.composable.component

import android.view.KeyEvent.ACTION_DOWN
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.duncanclark.alltrailsproject.R

@Composable
fun SearchBar(
    modifier: Modifier,
    onSearch: (String) -> Unit
) {
    var query by remember { mutableStateOf("") }

    Column(
        modifier = modifier.padding(bottom = 12.dp)
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text(stringResource(R.string.search_bar)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 36.dp, top = 6.dp, end = 36.dp, bottom = 24.dp)
                .onPreviewKeyEvent {
                    // Handles wired keyboards
                    if (it.key == Key.Enter && it.nativeKeyEvent.action == ACTION_DOWN) {
                        if (query.isNotEmpty()) { onSearch(query) }
                        true
                    } else {
                        false
                    }
                },
            shape = RoundedCornerShape(50),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                if (query.isNotEmpty()) {
                    onSearch(query)
                }
            })
        )
    }
}