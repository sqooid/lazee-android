package com.example.lazee.components.preferences

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp

@Composable
fun SwitchPreference(
    title: String,
    subtitle: String = "",
    initialValue: Boolean = false,
    onChange: (value: Boolean) -> Unit = {},
    disabled: Boolean = false
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    var value by remember {
        mutableStateOf(initialValue)
    }

    val onCheck = {
        value = !value
        onChange(value)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                enabled = !disabled,
                indication = LocalIndication.current,
                interactionSource = interactionSource,
                onClick = onCheck
            )
            .padding(PaddingValues(horizontal = 32.dp, vertical = 16.dp))
            .alpha(
                if (disabled) {
                    0.4f
                } else {
                    1.0f
                }
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(weight = 1f, fill = true)
                .padding(PaddingValues(end = 12.dp))
        ) {

            Text(text = title, style = MaterialTheme.typography.bodyLarge)
            if (subtitle != "")
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.alpha(0.5f)
                )
        }
        Switch(
            checked = value,
            onCheckedChange = { _ -> onCheck() },
            interactionSource = interactionSource,
            enabled = !disabled
        )
    }
}