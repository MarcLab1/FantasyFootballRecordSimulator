package com.fantasyfootballrecordsimulator

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fantasyfootballrecordsimulator.ui.presentation.FFViewModel

@Composable
fun NumberOfTeamsSpinner(vm: FFViewModel)
{   var expandedTeams by remember { mutableStateOf(false) }
    var teamsList: MutableList<Int> = mutableListOf<Int>()
    teamsList.addAll(listOf(4, 6, 8, 10, 12, 14, 16, 18, 20))

    Row(
        modifier = Modifier
            .border(
                BorderStroke(1.dp, MaterialTheme.colors.primary),
                shape = RoundedCornerShape(10)
            )
            .padding(all = 10.dp)
    )
    {
        Text("Number of teams:  ")
        Text(vm.numberTeams.value.toString())
        Box {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "",
                tint = MaterialTheme.colors.primary,
                modifier = Modifier
                    .clickable {
                        expandedTeams = true
                    }
            )
            DropdownMenu(
                expanded = expandedTeams,
                onDismissRequest = { expandedTeams = false },
            ) {
                teamsList.forEach { item ->
                    DropdownMenuItem(onClick = {
                        expandedTeams = false
                        vm.updateNumberTeams(item)
                    }
                    )
                    {
                        Text(text = item.toString())
                    }
                }
            }
        }
    }
}