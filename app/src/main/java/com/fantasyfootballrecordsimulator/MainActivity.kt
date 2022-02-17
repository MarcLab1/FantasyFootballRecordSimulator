package com.fantasyfootballrecordsimulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.fantasyfootballrecordsimulator.ui.presentation.FFViewModel
import com.fantasyfootballrecordsimulator.ui.presentation.navigation.Navigation
import com.fantasyfootballrecordsimulator.ui.theme.FantasyFootballRecordSimulatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FantasyFootballRecordSimulatorTheme {
                var nav = rememberNavController()
                var vm: FFViewModel = viewModel()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation(vm, nav)
                }
            }
        }
    }
}
