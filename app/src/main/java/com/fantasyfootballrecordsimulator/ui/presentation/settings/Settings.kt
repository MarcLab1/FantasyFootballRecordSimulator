package com.fantasyfootballrecordsimulator.ui.presentation.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fantasyfootballrecordsimulator.NumberOfPlayoffTeamsSpinner
import com.fantasyfootballrecordsimulator.NumberOfTeamsSpinner
import com.fantasyfootballrecordsimulator.NumberOfWeeksSpinner
import com.fantasyfootballrecordsimulator.ui.presentation.FFViewModel
import com.fantasyfootballrecordsimulator.ui.presentation.load.LoadSettingsAppBar

@Composable
fun Settings(vm: FFViewModel, nav: NavController)
{
    Scaffold(
        topBar = { LoadSettingsAppBar(nav) },
        content = {
            SettingsContent(vm)
        }
    )
}

@Composable
fun SettingsContent(vm: FFViewModel)
{
    Column(modifier = Modifier.fillMaxSize().padding(top = 10.dp, bottom = 60.dp), horizontalAlignment = Alignment.CenterHorizontally)
    {
        NumberOfTeamsSpinner(vm)
        Spacer(modifier = Modifier.padding(5.dp))
        NumberOfWeeksSpinner(vm)
        Spacer(modifier = Modifier.padding(5.dp))
        NumberOfPlayoffTeamsSpinner(vm)
    }
}
