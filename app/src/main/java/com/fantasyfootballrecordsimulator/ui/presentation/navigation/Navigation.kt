package com.fantasyfootballrecordsimulator.ui.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fantasyfootballrecordsimulator.Home
import com.fantasyfootballrecordsimulator.ui.presentation.FFViewModel
import com.fantasyfootballrecordsimulator.ui.presentation.load.Load
import com.fantasyfootballrecordsimulator.ui.presentation.settings.Settings

@Composable
fun Navigation(vm: FFViewModel, nav: NavController) {

    NavHost(navController = nav as NavHostController, startDestination = Routes.HOME.route)
    {
        composable(Routes.HOME.route)
        {
            Home(vm, nav)
        }
        composable(Routes.SETTINGS.route)
        {
            Settings(vm, nav)
        }
        composable(Routes.LOAD.route)
        {
            Load(vm, nav)
        }
    }
}

enum class Routes(val route: String, val label: String, val iv: ImageVector) {
    HOME("home", "Home", Icons.Filled.Home),
    SETTINGS("settings", "Settings", Icons.Filled.Settings),
    LOAD("load", "Load", Icons.Filled.AddCircle),
}

