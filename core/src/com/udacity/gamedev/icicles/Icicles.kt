package com.udacity.gamedev.icicles

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.DelayedRemovalArray
import com.badlogic.gdx.utils.viewport.Viewport
import com.udacity.gamedev.icicles.Constants.Companion.ICICLE_COLOR
import com.udacity.gamedev.icicles.Constants.Companion.ICICLE_HEIGHT
import com.udacity.gamedev.icicles.Constants.Companion.SPAWNS_PER_SECOND

class Icicles(private val viewport: Viewport, var dodgedIcicles: Int = 0) {

    init {
        init()
    }

    private val TAG = Icicles::class.java.simpleName

    // Use a DelayedRemovalArray to hold our icicles
    lateinit var icicleList: DelayedRemovalArray<Icicle>

    fun init() {
        // Initialize the DelayedRemovalArray
        icicleList = DelayedRemovalArray(false, 10)
        // Set icicles dodged count to zero
        dodgedIcicles = 0
    }

    fun update(delta: Float) {

        // Replace hard-coded spawn rate with a constant
        // Add a new icicle at the top of the viewport at a random x position
        if (MathUtils.random() < delta * SPAWNS_PER_SECOND)
            icicleList.add(Icicle(Vector2(
                    MathUtils.random() * viewport.worldWidth,
                    viewport.worldHeight
            )))

        // begin update and removal session
        icicleList.begin()

        for (icicle in icicleList) {

            with(icicle) {

                // Update each icicle
                update(delta)

                // Remove any icicle completely off the bottom of the screen
                if (position.y < -ICICLE_HEIGHT){
                    icicleList.removeValue(this, true)
                    // Increment count of icicles dodged
                    dodgedIcicles++
                }
            }
        }
        // End removal session
        icicleList.end()

//            Gdx.app.log(TAG, "icicleList size: ${icicleList.size}")

    }

    fun render(renderer: ShapeRenderer) {
        // Set ShapeRenderer Color
        renderer.color = ICICLE_COLOR

        // Render each icicle
        for (icicle in icicleList) icicle.render(renderer)
    }
}

