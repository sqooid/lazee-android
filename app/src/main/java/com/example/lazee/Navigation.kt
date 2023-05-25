package com.example.lazee

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lazee.fragments.ConfigPage
import com.example.lazee.fragments.MainPage
import com.example.lazee.fragments.SettingsPage

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Main.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Main.route) { MainPage(navController) }
        composable(Screen.Config.route) { ConfigPage(navController) }
        composable(Screen.Settings.route) { SettingsPage(navController) }
    }
}

@Composable
fun NavBar(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val items = listOf(
        Screen.Main,
        Screen.Config,
        Screen.Settings
    )
    var selectedItem by remember { mutableStateOf(0) }
    NavigationBar(modifier = modifier) {
        items.forEachIndexed { index, screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon, contentDescription = screen.route) },
                selected = selectedItem == index,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                    selectedItem = index
                },
                label = { Text(text = screen.route) })
        }
    }
}

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Main : Screen("main", R.string.main, Icons.Filled.Home)
    object Config : Screen("config", R.string.config, Icons.Filled.Build)
    object Settings : Screen("settings", R.string.settings, Icons.Filled.Settings)
}