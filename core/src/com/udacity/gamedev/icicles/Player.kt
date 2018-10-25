package com.udacity.gamedev.icicles

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.Viewport
import com.udacity.gamedev.icicles.Constants.Companion.ACCELEROMETER_SENSITIVITY
import com.udacity.gamedev.icicles.Constants.Companion.GRAVITATIONAL_ACCELERATION
import com.udacity.gamedev.icicles.Constants.Companion.HEAD_HEIGHT
import com.udacity.gamedev.icicles.Constants.Companion.HEAD_RADIUS
import com.udacity.gamedev.icicles.Constants.Companion.HEAD_SEGMENTS
import com.udacity.gamedev.icicles.Constants.Companion.LIMB_WIDTH
import com.udacity.gamedev.icicles.Constants.Companion.PLAYER_SPEED

class Player(private val viewport: Viewport,
             var deathCount: Int = 0,
             private var position: Vector2 = Vector2(viewport.worldWidth / 2, HEAD_HEIGHT)) {

    val TAG = Player::class.java.simpleName

    fun init() {
        // moves the character to the bottom center of the screen
        position = Vector2(viewport.worldWidth / 2, HEAD_HEIGHT)
    }

    // Render function that accepts a ShapeRenderer and does the actual drawing
    fun render(renderer: ShapeRenderer) {

        // Set the ShapeType
        renderer.set(ShapeRenderer.ShapeType.Filled)

        // Set the ShapeRenderer's color
        renderer.color = Constants.PLAYER_COLOR

        renderer.circle(position.x, position.y, HEAD_RADIUS, HEAD_SEGMENTS)

        val torsoTop = Vector2(position.x, position.y - HEAD_RADIUS)
        val torsoBottom = Vector2(torsoTop.x, torsoTop.y - 2 * HEAD_RADIUS)

        renderer.rectLine(torsoTop, torsoBottom, LIMB_WIDTH)

        renderer.rectLine(
                torsoTop.x,
                torsoTop.y,
                torsoTop.x + HEAD_RADIUS,
                torsoTop.y - HEAD_RADIUS,
                LIMB_WIDTH
        )
        renderer.rectLine(
                torsoTop.x,
                torsoTop.y,
                torsoTop.x - HEAD_RADIUS,
                torsoTop.y - HEAD_RADIUS,
                LIMB_WIDTH
        )

        renderer.rectLine(
                torsoBottom.x,
                torsoBottom.y,
                torsoBottom.x + HEAD_RADIUS,
                torsoBottom.y - HEAD_RADIUS,
                LIMB_WIDTH
        )

        renderer.rectLine(
                torsoBottom.x,
                torsoBottom.y,
                torsoBottom.x - HEAD_RADIUS,
                torsoBottom.y - HEAD_RADIUS,
                LIMB_WIDTH
        )
    }

    fun update(delta: Float) {

        // Use Gdx.input.isKeyPressed() to move the player
        // in the appropriate direction when an arrow key is pressed
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= delta * PLAYER_SPEED
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += delta * PLAYER_SPEED
        }

        // Accelerometer Movement

        // Compute accelerometer input = raw input / (gravity * sensitivity)
        val accelerometerInput = Gdx.input.accelerometerY /
                (GRAVITATIONAL_ACCELERATION * ACCELEROMETER_SENSITIVITY)

        position.x += delta * accelerometerInput * PLAYER_SPEED

        ensureInBounds()
    }

    fun hitByIcicle(icicles: Icicles): Boolean {

        // Loop over icicles, checking if the point of any icicle is within the player's head
        for (icicle in icicles.icicleList)
            if (icicle.position.dst(position) < HEAD_RADIUS) {
                // If the player was hit, increment death counter
                deathCount++
                return true
            }

        return false
    }

    private fun ensureInBounds() {
        if (position.x < HEAD_RADIUS) position.x = HEAD_RADIUS
        if (position.x > (viewport.worldWidth - HEAD_RADIUS))
            position.x = viewport.worldWidth - HEAD_RADIUS
    }
}
