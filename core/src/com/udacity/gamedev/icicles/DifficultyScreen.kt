package com.udacity.gamedev.icicles

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.FitViewport
import com.udacity.gamedev.icicles.Constants.Companion.BACKGROUND_COLOR
import com.udacity.gamedev.icicles.Constants.Companion.BUTTON_RADIUS
import com.udacity.gamedev.icicles.Constants.Companion.COLDER_LEVEL_CENTER
import com.udacity.gamedev.icicles.Constants.Companion.COLDER_LEVEL_COLOR
import com.udacity.gamedev.icicles.Constants.Companion.COLDEST_LEVEL_CENTER
import com.udacity.gamedev.icicles.Constants.Companion.COLDEST_LEVEL_COLOR
import com.udacity.gamedev.icicles.Constants.Companion.COLD_LEVEL_CENTER
import com.udacity.gamedev.icicles.Constants.Companion.COLD_LEVEL_COLOR
import com.udacity.gamedev.icicles.Constants.Companion.DIFFICULTY_WORLD_SIZE

class DifficultyScreen (private val game: IciclesGame,
                        private val renderer: ShapeRenderer = ShapeRenderer(),
                        private val batch: SpriteBatch = SpriteBatch(),
                        private val viewport: FitViewport = FitViewport(DIFFICULTY_WORLD_SIZE, DIFFICULTY_WORLD_SIZE),
                        private val font: BitmapFont = BitmapFont()): Screen, InputAdapter() {

    private val TAG = DifficultyScreen::class.java.simpleName

    override fun show() {
        Gdx.input.inputProcessor = this

        // Set the font scale using the constant
        font.data.setScale(Constants.DIFFICULTY_LABELS_SCALE)

        font.region.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)

    }

    override fun render(delta: Float) {

        // Apply the viewport
        viewport.apply()

        Gdx.gl.glClearColor(
                BACKGROUND_COLOR.r,
                BACKGROUND_COLOR.g,
                BACKGROUND_COLOR.b,
                1f
        )
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        // Set the ShapeRenderer's projection matrix
        renderer.projectionMatrix = viewport.camera.combined

        renderer.begin(ShapeRenderer.ShapeType.Filled)

        renderer.set(ShapeRenderer.ShapeType.Filled)
        renderer.color = COLD_LEVEL_COLOR
        renderer.circle(COLD_LEVEL_CENTER.x,
                COLD_LEVEL_CENTER.y,
                Constants.BUTTON_RADIUS,
                36
        )
        renderer.color = COLDER_LEVEL_COLOR
        renderer.circle(
                COLDER_LEVEL_CENTER.x,
                COLDER_LEVEL_CENTER.y,
                Constants.BUTTON_RADIUS,
                36
        )

        renderer.color = COLDEST_LEVEL_COLOR
        renderer.circle(
                COLDEST_LEVEL_CENTER.x,
                COLDEST_LEVEL_CENTER.y,
                Constants.BUTTON_RADIUS,
                36
        )
        renderer.end()

        // Set the SpriteBatche's projection matrix
        batch.projectionMatrix = viewport.camera.combined

        // Use SpriteBatch to draw the labels on the buttons
        // HINT: Use GlyphLayout to get vertical centering
        batch.begin()

        val coldLayout = GlyphLayout(font, Constants.COLD)
        font.draw(
                batch,
                Constants.COLD,
                Constants.COLD_LEVEL_CENTER.x,
                Constants.COLD_LEVEL_CENTER.y + coldLayout.height / 2,
                0f,
                Align.center,
                false
        )

        val colderLayout = GlyphLayout(font, Constants.COLDER)
        font.draw(
                batch,
                Constants.COLDER,
                Constants.COLDER_LEVEL_CENTER.x,
                Constants.COLDER_LEVEL_CENTER.y + colderLayout.height / 2,
                0f,
                Align.center,
                false
        )

        val coldestLayout = GlyphLayout(font, Constants.COLDEST)
        font.draw(
                batch,
                Constants.COLDEST,
                Constants.COLDEST_LEVEL_CENTER.x,
                Constants.COLDEST_LEVEL_CENTER.y + coldestLayout.height / 2,
                0f,
                Align.center,
                false
        )

        batch.end()

    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun hide() {
        batch.dispose()
        font.dispose()
        renderer.dispose()
    }

    override fun dispose() {

    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {

        // Unproject the touch from the screen to the world
        val worldTouch = viewport.unproject(Vector2(screenX.toFloat(), screenY.toFloat()))

        // Check if the touch was inside a button,
        // and launch the icicles screen with the appropriate difficulty

        if (worldTouch.dst(COLD_LEVEL_CENTER) < BUTTON_RADIUS) {
            game.showIciclesScreen(Constants.DIFFICULTY.COLD)
        }

        if (worldTouch.dst(COLDER_LEVEL_CENTER) < BUTTON_RADIUS) {
            game.showIciclesScreen(Constants.DIFFICULTY.COLDER)
        }

        if (worldTouch.dst(COLDEST_LEVEL_CENTER) < BUTTON_RADIUS) {
            game.showIciclesScreen(Constants.DIFFICULTY.COLDEST)
        }

        return true
    }
}
