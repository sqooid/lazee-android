package com.example.lazee.components.preferences

import android.content.SharedPreferences
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
    sharedPreferences: SharedPreferences,
    key: String,
    title: String,
    subtitle: String = "",
    defaultValue: Boolean = false,
    onChange: (value: Boolean) -> Unit = {},
    disabled: Boolean = false
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    var value by remember {
        mutableStateOf(sharedPreferences.getBoolean(key, defaultValue))
    }

    val onCheck = {
        value = !value
        onChange(value)
        sharedPreferences
            .edit()
            .putBoolean(key, value)
            .apply()
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
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(weight = 1f, fill = true)) {

            Text(text = title, style = MaterialTheme.typography.bodyMedium)
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
            interactionSource = interactionSource
        )
    }
}