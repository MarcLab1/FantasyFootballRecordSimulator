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
fun NumberOfPlayoffTeamsSpinner(vm : FFViewModel)
{   var expanded by remember { mutableStateOf(false) }
    var playoffTeamsList: MutableList<Int> = mutableListOf<Int>()
    playoffTeamsList.addAll(listOf(2,4,6,8,10))

    Row(
        modifier = Modifier
            .border(
                BorderStroke(1.dp, MaterialTheme.colors.primary),
                shape = RoundedCornerShape(10)
            )
            .padding(all = 10.dp)
    )
    {
        Text("Number of playoff teams:  ")
        Text(vm.numberPlayoffTeams.value.toString())
        Box {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "",
                tint = MaterialTheme.colors.primary,
                modifier = Modifier
                    .clickable {
                        expanded = true
                    }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                playoffTeamsList.forEach { item ->
                    DropdownMenuItem(onClick = {
                        expanded = false
                        vm.numberPlayoffTeams.value = item
                    })
                    {
                        Text(text = item.toString())
                    }
                }
            }
        }
    }
}