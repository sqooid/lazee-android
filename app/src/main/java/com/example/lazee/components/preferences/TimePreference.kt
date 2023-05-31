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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.example.lazee.components.TimePickerDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePreference(
    title: String,
    subtitle: String = "",
    initialValue: Time = Time(0, 0),
    onChange: (value: Time) -> Unit = {},
    disabled: Boolean = false
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    var showTimePicker by remember { mutableStateOf(false) }
    val state = rememberTimePickerState(
        initialHour = initialValue.hour,
        initialMinute = initialValue.minute
    )

    val onClick = {
        showTimePicker = true
    }

    if (showTimePicker) {
        TimePickerDialog(onCancel = { showTimePicker = false }, onConfirm = {
            showTimePicker = false
            onChange(Time(state.hour, state.minute))
        }) {
            TimePicker(state = state)
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                enabled = !disabled,
                indication = LocalIndication.current,
                interactionSource = interactionSource,
                onClick = onClick
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
        Text(text = Time(state.hour, state.minute).to12HourString())
    }
}