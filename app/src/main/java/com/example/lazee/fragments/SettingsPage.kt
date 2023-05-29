package com.example.lazee.fragments

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.lazee.Constants
import com.example.lazee.components.TimePickerDialog
import com.example.lazee.components.preferences.SwitchPreference

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsPage(navHostController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences =
        context.getSharedPreferences(Constants.PREFERENCES_NAME, Context.MODE_PRIVATE)

    var showTimePicker by remember { mutableStateOf(false) }
    val state = rememberTimePickerState()

    Column(
        modifier = Modifier
            .fillMaxWidth().padding(paddingValues = PaddingValues(vertical = 12.dp))
    ) {
        SwitchPreference(
            sharedPreferences = sharedPreferences,
            key = "test",
            title = "Turn something on",
        )
        Button(onClick = { showTimePicker = true }) {

        }
    }
    if (showTimePicker) {
        TimePickerDialog(onCancel = { showTimePicker = false }, onConfirm = {
            showTimePicker = false
        }) {
            TimePicker(state = state)
        }
    }
}
