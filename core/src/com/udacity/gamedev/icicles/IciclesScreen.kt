package com.udacity.gamedev.icicles

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.udacity.gamedev.icicles.Constants.Companion.BACKGROUND_COLOR
import com.udacity.gamedev.icicles.Constants.Companion.WORLD_SIZE


class IciclesScreen : Screen {

    private val TAG = IciclesScreen::class.java.name

    // A ShapeRenderer
    private lateinit var renderer: ShapeRenderer

    // An ExtendViewport
    private lateinit var iciclesViewport: ExtendViewport

    // An Icicle
    private lateinit var icicle: Icicle

    // Player
    private lateinit var player: Player

    // An instance of Icicles
    private lateinit var icicles: Icicles

    override fun show() {

        // Initialize the ShapeRenderer
        renderer = ShapeRenderer()

        // Set autoShapeType(true) on the ShapeRenderer
        renderer.setAutoShapeType(true)

        // Initialize the iciclesViewport using the world size constant
        iciclesViewport = ExtendViewport(WORLD_SIZE, WORLD_SIZE)

        // Initialize the player
        player = Player(iciclesViewport)

        // Initialize icicles
        icicles = Icicles(iciclesViewport)
    }

    override fun resize(width: Int, height: Int) {
        // Ensure that the iciclesViewport updates correctly
        iciclesViewport.update(width, height, true)
        player.init()
        icicles.init()
    }

    override fun dispose() {

    }

    override fun render(delta: Float) {

        // Call update() on icicles
        icicles.update(delta)

        // Call update() on player
        player.update(delta)

        // Apply the iciclesViewport
        iciclesViewport.apply()

        // Clear the screen to the background color
        Gdx.gl.glClearColor(
                BACKGROUND_COLOR.r,
                BACKGROUND_COLOR.g,
                BACKGROUND_COLOR.b,
                1f
        )
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT)

        // Set the ShapeRenderer's projection matrix
        renderer.projectionMatrix = iciclesViewport.camera.combined

        // Draw the Icicle
        renderer.begin()

        // Call render() on the icicles
        icicles.render(renderer)

        // Call render() on the player
        player.render(renderer)

        renderer.end()

    }

    override fun pause() {

    }

    override fun resume() {

    }


    override fun hide() {
        // Dispose of the ShapeRenderer
        renderer.dispose()

    }


}
