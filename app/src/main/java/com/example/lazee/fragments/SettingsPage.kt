package com.example.lazee.fragments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.lazee.components.preferences.PreferenceCategory
import com.example.lazee.components.preferences.SwitchPreference
import com.example.lazee.components.preferences.TimePreference

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsPage(navHostController: NavHostController, viewModel: SettingsViewModel = viewModel()) {
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues = PaddingValues(vertical = 12.dp))
    ) {
        PreferenceCategory(title = "Wake-up")
        SwitchPreference(
            disabled = true,
            title = "Unlock device to log wake-up",
            subtitle = "Device must be unlocked before wake-up event is logged",
            onChange = {
                viewModel.preferenceManager.wakeOnUnlock = it
            },
            initialValue = viewModel.preferenceManager.wakeOnUnlock

        )

        PreferenceCategory(title = "Sleep")
        TimePreference(
            title = "Earliest sleep time",
            subtitle = "Device shutdown will only be logged as a sleep event if it occurs after this time",
            initialValue = viewModel.preferenceManager.shutdownMinTime,
            onChange = { viewModel.preferenceManager.shutdownMinTime = it })
        TimePreference(
            title = "Latest sleep time",
            subtitle = "Device shutdown will only be logged as a sleep event if it occurs before this time",
            initialValue = viewModel.preferenceManager.shutdownMaxTime,
            onChange = { viewModel.preferenceManager.shutdownMaxTime = it })
    }
}
