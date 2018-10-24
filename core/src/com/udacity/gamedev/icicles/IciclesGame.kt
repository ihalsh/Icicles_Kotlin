package com.udacity.gamedev.icicles

import com.badlogic.gdx.Game

class IciclesGame : Game() {

    override fun create() {
        //Call setScreen() with a new IciclesScreen()
        setScreen(IciclesScreen())
    }
}
