package com.fantasyfootballrecordsimulator

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fantasyfootballrecordsimulator.ui.presentation.Team
import com.fantasyfootballrecordsimulator.ui.presentation.FFViewModel
import com.fantasyfootballrecordsimulator.ui.presentation.navigation.Routes
import com.fantasyfootballrecordsimulator.util.Constants

@Composable
fun Home(vm: FFViewModel, nav: NavController) {
    Scaffold(

        topBar = { HomeAppBar(nav) },
        content = {
            HomeContent(vm)
        }
    )
}

@Composable
fun HomeAppBar(nav: NavController) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Row(
                horizontalArrangement = Arrangement.Start, modifier = Modifier.padding(10.dp)
            )
            {
                Text(Constants.TITLE)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(), horizontalArrangement = Arrangement.End
            )
            {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Settings",
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .clickable {
                            nav.navigate(Routes.SETTINGS.route)
                        }
                )

                Icon(
                    imageVector = Icons.Filled.AddCircle,
                    contentDescription = "Load",
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .clickable {
                            nav.navigate(Routes.LOAD.route)
                        }
                )
            }
        }
    }
}

@Composable
fun HomeContent(vm: FFViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp, bottom = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        item()
        {
            Button(onClick = { vm.simulateSeason() }) {
                Text("simulate")
            }
            Spacer(modifier = Modifier.padding(5.dp))
        }
        itemsIndexed(vm.teams)
        { index, team ->
            TeamPage(index, team, vm.numberPlayoffTeams.value)
        }
    }
}

@Composable
fun TeamPage(index: Int, team: Team, numberPlayoffTeams: Int) {
    if (index < numberPlayoffTeams)
        Text(text = team.toString(), style = MaterialTheme.typography.body2)
    else
        Text(text = team.toString(), style = MaterialTheme.typography.body1)
}

@Composable
fun TestingTesting123(vm: FFViewModel) {
    Column() {
        Text("teams size = ${vm.teams.size}")
        Text("pop'd teams = ${vm.populatedTeams.size}")
    }
}