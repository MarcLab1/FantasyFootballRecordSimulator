package com.fantasyfootballrecordsimulator.ui.presentation.load

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fantasyfootballrecordsimulator.ui.presentation.Team
import com.fantasyfootballrecordsimulator.ui.presentation.FFViewModel

@Composable
fun Load(vm: FFViewModel, nav: NavController) {

    Scaffold(
        topBar = { LoadSettingsAppBar(nav) },
        content = {
            LoadContent(vm)
        }
    )
}

@Composable
fun LoadSettingsAppBar(nav: NavController) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(), horizontalArrangement = Arrangement.Start
        )
        {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .padding(start = 10.dp, end = 20.dp)
                    .clickable {
                        nav.navigateUp()
                    }
            )
        }
    }
}

@Composable
fun LoadContent(vm: FFViewModel) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp, bottom = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        item()
        {
            Row(modifier = Modifier.fillMaxWidth())
            {
                Text(
                    "Team Name", modifier = Modifier
                        .fillMaxWidth(.7f)
                        .padding(2.dp),
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center
                )
                Text(
                    "W", modifier = Modifier
                        .fillMaxWidth(.5f)
                        .padding(2.dp),
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center
                )
                Text(
                    "L",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp),
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center
                )
            }
        }

        itemsIndexed(vm.populatedTeams)
        { index, team ->
            if (index < vm.numberTeams.value)
                LoadTeamTextFields(index, team, vm)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoadTeamTextFields(index: Int, team: Team, vm: FFViewModel) {
    var name = remember { mutableStateOf(team.teamName) }
    var wins = remember { mutableStateOf(team.wins) }
    var losses = remember { mutableStateOf(team.losses) }

    Row(modifier = Modifier.fillMaxWidth())
    {
        val keyboardController = LocalSoftwareKeyboardController.current

        TextField(
            value = name.value,
            onValueChange = {
                name.value = it
                vm.populatedTeams[index].teamName = it
            },
            placeholder = { Text("Team") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
            maxLines = 1,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(.7f)
                .padding(2.dp),

        )
        TextField(
            value = if (wins.value == 0) "" else wins.value.toString(),
            onValueChange = {
                val w = it.toIntOrNull()
                when (w) {
                    null -> {
                        wins.value = 0
                        vm.populatedTeams[index].wins = 0
                    }
                    else -> {
                        wins.value = w
                        vm.populatedTeams[index].wins = w
                    }
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
            modifier = Modifier
                .fillMaxWidth(.5f)
                .padding(2.dp),
            placeholder = { Text(text = "0") },
            maxLines = 1,
            singleLine = true,
        )
        TextField(
            value = if (losses.value == 0) "" else losses.value.toString(),
            onValueChange = {
                val l = it.toIntOrNull()
                when (l) {
                    null -> {
                        losses.value = 0
                        vm.populatedTeams[index].losses = 0
                    }
                    else -> {
                        losses.value = l
                        vm.populatedTeams[index].losses = l
                    }
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            placeholder = { Text(text = "0") },
            maxLines = 1,
            singleLine = true
        )
    }
}