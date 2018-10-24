package com.udacity.gamedev.icicles

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.Viewport
import com.udacity.gamedev.icicles.Constants.Companion.HEAD_HEIGHT
import com.udacity.gamedev.icicles.Constants.Companion.HEAD_RADIUS
import com.udacity.gamedev.icicles.Constants.Companion.HEAD_SEGMENTS
import com.udacity.gamedev.icicles.Constants.Companion.LIMB_WIDTH

class Player(private val viewport: Viewport) {

    init {
        init()
    }

    val TAG = Player::class.java.name

    // Position
    private lateinit var position: Vector2

    // Function that moves the character to the bottom center of the screen
    fun init() {
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

}
