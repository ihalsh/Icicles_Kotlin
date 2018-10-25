package com.udacity.gamedev.icicles

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.viewport.Viewport
import com.udacity.gamedev.icicles.Constants.Companion.ICICLE_COLOR
import com.udacity.gamedev.icicles.Constants.Companion.SPAWNS_PER_SECOND

class Icicles(private val viewport: Viewport) {

    init {
        init()
    }

    val TAG = Icicles::class.java.name

    // An array of Icicle
    private lateinit var icicleList: Array<Icicle>

    fun init() {
        // Initialize icicleList
        icicleList = Array(false, 10)
    }

    fun update(delta: Float) {

        // Replace hard-coded spawn rate with a constant
        // Add a new icicle at the top of the viewport at a random x position
        if (MathUtils.random() < delta * SPAWNS_PER_SECOND)
            icicleList.add(Icicle(Vector2(
                    MathUtils.random() * viewport.worldWidth,
                    viewport.worldHeight
            )))

        // Update each icicle
        for (icicle in icicleList) icicle.update(delta)
    }

    fun render(renderer: ShapeRenderer) {
        // Set ShapeRenderer Color
        renderer.color = ICICLE_COLOR

        // Render each icicle
        for (icicle in icicleList) icicle.render(renderer)
    }
}

