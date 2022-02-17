package com.fantasyfootballrecordsimulator.ui.presentation

import kotlin.random.Random

class Team {
    var wins: Int = 0
    var losses: Int = 0
    var teamIndex: Int = 0
    var teamName: String

    constructor(teamIndex: Int) {
        this.teamIndex = teamIndex + 1
        this.teamName = "Team " + (this.teamIndex)
    }

    constructor(team: Team)
    {
        this.teamIndex = team.teamIndex
        this.teamName = team.teamName
        this.wins = team.wins
        this.losses = team.losses
    }

    fun playWeek(team: Team) {
        if (Random.nextBoolean()) {
            wins++
            team.losses++
        } else {
            losses++
            team.wins++
        }
    }

    fun swap(team: Team) {
        var tempW: Int = team.wins
        var tempL: Int = team.losses
        var tempIndex: Int = team.teamIndex
        var tempName: String = team.teamName
        team.wins = this.wins
        team.losses = this.losses
        team.teamIndex = this.teamIndex
        team.teamName = this.teamName
        this.wins = tempW
        this.losses = tempL
        this.teamIndex = tempIndex
        this.teamName = tempName

    }

    override fun toString(): String {
        return teamName + " - W:" + wins + " | L:" + losses
    }
}