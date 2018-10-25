package com.udacity.gamedev.icicles

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.udacity.gamedev.icicles.Constants.Companion.BACKGROUND_COLOR
import com.udacity.gamedev.icicles.Constants.Companion.HUD_MARGIN
import com.udacity.gamedev.icicles.Constants.Companion.WORLD_SIZE
import kotlin.math.max

class IciclesScreen(var topScore: Int = 0,
                    private val difficulty: Constants.DIFFICULTY = Constants.DIFFICULTY.COLD,
                    private val renderer: ShapeRenderer = ShapeRenderer(),
                    private val iciclesViewport: ExtendViewport = ExtendViewport(WORLD_SIZE, WORLD_SIZE),
                    private val player: Player = Player(iciclesViewport),
                    private val icicles: Icicles = Icicles(iciclesViewport, difficulty = difficulty),
                    private val hudViewport: ScreenViewport = ScreenViewport(),
                    private val batch: SpriteBatch = SpriteBatch(),
                    private val font: BitmapFont = BitmapFont()) : Screen {

    private val TAG = IciclesScreen::class.java.name

    override fun show() {

        // Set autoShapeType(true) on the ShapeRenderer
        renderer.setAutoShapeType(true)

        // Give the font a linear TextureFilter
        font.region.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
    }

    override fun resize(width: Int, height: Int) {
        // Ensure that the iciclesViewport updates correctly
        iciclesViewport.update(width, height, true)
        player.init()
        icicles.init()

        // Update HUD viewport
        hudViewport.update(width, height, true)

        // Set font scale to min(width, height) / reference screen size
        font.data.setScale(Math.min(width, height) / Constants.HUD_FONT_REFERENCE_SCREEN_SIZE)
    }

    override fun dispose() {
        renderer.dispose()
        batch.dispose()
        font.dispose()
    }

    override fun render(delta: Float) {

        // Call update() on icicles
        icicles.update(delta)

        // Call update() on player
        player.update(delta)

        // Check if the player was hit by an icicle. If so, reset the icicles.
        if (player.hitByIcicle(icicles)) icicles.init()

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

        // Set the top score to max(topScore, iciclesDodges)
        topScore = max(topScore, icicles.dodgedIcicles)

        // Apply the HUD viewport
        hudViewport.apply()

        // SpriteBatch's projection matrix
        batch.projectionMatrix = hudViewport.camera.combined

        // Begin the SpriteBatch
        batch.begin()

        // Draw the number of player deaths in the top left
        font.draw(
                batch,
                "Death = ${player.deathCount}\nDifficulty: ${difficulty.level}",
                HUD_MARGIN,
                hudViewport.worldHeight - HUD_MARGIN
        )

        // Draw the score and top score in the top right
        font.draw(
                batch,
                "Score: ${icicles.dodgedIcicles}\nTop Score: $topScore",
                hudViewport.worldWidth - Constants.HUD_MARGIN,
                hudViewport.worldHeight - Constants.HUD_MARGIN,
                0f,
                Align.right,
                false
        )
        // End the SpriteBatch
        batch.end()

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
