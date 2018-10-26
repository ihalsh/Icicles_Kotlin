package com.udacity.gamedev.icicles

import com.badlogic.gdx.Game

class IciclesGame : Game() {

    override fun create() {
        showDifficultyScreen()
    }

    fun showDifficultyScreen() {
        // Show the difficulty screen
        setScreen(DifficultyScreen(this))
    }

    fun showIciclesScreen(difficulty: Constants.DIFFICULTY) {
        // Show the Icicles screen with the appropriate difficulty
        setScreen(IciclesScreen(difficulty = difficulty, game = this))
    }
}
