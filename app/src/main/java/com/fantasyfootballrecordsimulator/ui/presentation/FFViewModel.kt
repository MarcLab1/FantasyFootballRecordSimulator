package com.fantasyfootballrecordsimulator.ui.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class FFViewModel : ViewModel() {

    var teams = mutableStateListOf<Team>()
    var populatedTeams = mutableStateListOf<Team>()
    var numberTeams: MutableState<Int> = mutableStateOf(12)
    var numberWeeks: MutableState<Int> = mutableStateOf(15)
    var numberPlayoffTeams: MutableState<Int> = mutableStateOf(4)
    var numberWeeksPlayed: Int = 0

    init {
        resetSeason()
    }

    fun resetSeason() {
        teams.clear()
        numberWeeksPlayed = 0

        if (populatedTeams.size == 0) {
            loadEmptyTeams()
            loadEmptyPopulatedTeams()
        } else {
            loadPopulatedTeams()
        }
    }

    fun simulateSeason() {
        resetSeason()
        for (week in 1..(numberWeeks.value - numberWeeksPlayed)) {
            for (i in 0 until numberTeams.value - 1 step 2) {
                teams[i].playWeek(teams[i + 1])
            }
            //rotate array
            for (j in 1 until numberTeams.value - 1 step 2) {
                teams[j].swap(teams[j + 1])
                teams[j].swap(teams[j + 2])
            }
            numberWeeksPlayed++
        }
        teams.sortByDescending { it.wins }
    }

    fun loadPopulatedTeams() {
        for (i in 0 until numberTeams.value) {
            teams.add(Team(populatedTeams[i]))
        }
        //I've implemented zero error checking here by design
        //its really up to the user to load correct values
        numberWeeksPlayed = teams[0].wins + teams[0].losses
    }

    fun loadEmptyPopulatedTeams() {
        for (i in 0 until numberTeams.value)
            populatedTeams.add(Team(i))
    }

    fun loadEmptyTeams() {
        for (i in 0 until numberTeams.value)
            teams.add(Team(i))

        numberWeeksPlayed = 0
    }

    fun updateNumberTeams(number: Int) {
        //add pop'd teams
        if (numberTeams.value < number) {
            while (populatedTeams.size < number) {
                populatedTeams.add(Team(populatedTeams.size))
            }
        }
        //remove pop'd teams
        else {
            while (populatedTeams.size > number) {
                populatedTeams.removeLast()
            }
        }
        numberTeams.value = number
        resetSeason()
    }
}