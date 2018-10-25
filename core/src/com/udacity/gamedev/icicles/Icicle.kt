package com.udacity.gamedev.icicles

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.udacity.gamedev.icicles.Constants.Companion.ICICLE_ACCELERATION
import com.udacity.gamedev.icicles.Constants.Companion.ICICLE_HEIGHT
import com.udacity.gamedev.icicles.Constants.Companion.ICICLE_WIDTH

class Icicle(private val position: Vector2) {

    private val TAG = Icicle::class.java.name

    // Vector2 for velocity
    private var velocity = Vector2()

    fun update(delta: Float) {

        // Update velocity using icicle acceleration constant
        velocity.mulAdd(ICICLE_ACCELERATION, delta)

        // Update position using velocity
        position.mulAdd(velocity, delta)
    }

        // A render function that takes a ShapeRenderer
        fun render(renderer: ShapeRenderer) {

            // Set the ShapeType
            renderer.set(ShapeRenderer.ShapeType.Filled)

            // Set the ShapeRenderer's color
            renderer.color = Constants.ICICLE_COLOR

            // Draw the icicle using the size constants
            renderer.triangle(
                    position.x,
                    position.y,
                    position.x - ICICLE_WIDTH / 2,
                    position.y + ICICLE_HEIGHT,
                    position.x + ICICLE_WIDTH / 2,
                    position.y + ICICLE_HEIGHT
            )
    }
}
