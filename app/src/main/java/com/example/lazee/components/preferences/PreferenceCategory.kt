package com.example.lazee.components.preferences

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PreferenceCategory(modifier: Modifier = Modifier, title: String) {
    Text(
        text = title,
        modifier = modifier.padding(start = 32.dp, end = 32.dp, top = 24.dp, bottom = 8.dp),
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.labelLarge
    )
}