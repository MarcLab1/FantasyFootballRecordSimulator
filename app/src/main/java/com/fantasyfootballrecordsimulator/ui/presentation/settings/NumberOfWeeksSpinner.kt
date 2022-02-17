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
fun NumberOfWeeksSpinner(vm: FFViewModel) {
    var expandedWeeks by remember { mutableStateOf(false) }
    var weeksList: MutableList<Int> = mutableListOf<Int>()
    weeksList.addAll(listOf(12, 13, 14, 15, 16))

    Row(
        modifier = Modifier
            .border(
                BorderStroke(1.dp, MaterialTheme.colors.primary),
                shape = RoundedCornerShape(10)
            )
            .padding(all = 10.dp)
    ) {
        Text("Number of weeks:  ")
        Text(vm.numberWeeks.value.toString())
        Box {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "",
                tint = MaterialTheme.colors.primary,
                modifier = Modifier
                    .clickable {
                        expandedWeeks = true
                    }
            )
            DropdownMenu(
                expanded = expandedWeeks,
                onDismissRequest = { expandedWeeks = false },
            ) {
                weeksList.forEach { item ->
                    DropdownMenuItem(onClick = {
                        expandedWeeks = false
                        vm.numberWeeks.value = item

                    })
                    {
                        Text(text = item.toString())
                    }
                }
            }
        }
    }
}