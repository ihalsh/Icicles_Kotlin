package com.udacity.gamedev.icicles

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2

class Constants {

    companion object {

        // Constant for the background color of the world
        val BACKGROUND_COLOR = Color.BLUE

        // Constant for the color of the icicles
        val ICICLE_COLOR = Color.WHITE

        // Constant for the player's color
        val PLAYER_COLOR = Color.BLACK

        // Constant for the world size
        const val WORLD_SIZE = 10.0f

        // Constant for the height of the icicle
        const val ICICLE_HEIGHT = 1f

        // Constant for the width of the icicle
        const val ICICLE_WIDTH = 0.5f

        // Constant for icicle acceleration
        val ICICLE_ACCELERATION = Vector2(0f, -5.0f)

        // Constant for player head radius
        const val HEAD_RADIUS = 0.5f

        // Constant for player head height
        const val HEAD_HEIGHT = 4.0f * HEAD_RADIUS

        // Constant for player limb width
        const val LIMB_WIDTH = 0.2f

        // Constant for circle segments for the player's head
        const val HEAD_SEGMENTS = 20

        // Constant for player movement speed
        const val PLAYER_SPEED = 10

        // Constant for Accelerometer sensitivity
        const val ACCELEROMETER_SENSITIVITY = 0.5f

        // Constant for acceleration due to gravity (9.8)
        const val GRAVITATIONAL_ACCELERATION = 9.8f

        // Screen reference size for scaling the HUD (480 works well)
        const val HUD_FONT_REFERENCE_SCREEN_SIZE = 480.0f

        // Constant for the margin between the HUD and screen edge
        const val HUD_MARGIN = 20f

        // Constants for difficulty labels ("Cold", "Colder", "Coldest")
        const val COLD = "Cold"
        const val COLDER = "Colder"
        const val COLDEST = "Coldest"

        // Constants for the icicle spawn rates for the various difficulties
        const val COLD_SPAWN_RATE = 5f
        const val COLDER_SPAWN_RATE = 10f
        const val COLDEST_SPAWN_RATE = 15f
    }

    // Difficulty enum holding the spawn rate and label for each difficulty
    enum class DIFFICULTY(val level: String, val rate: Float) {
        COLD(level = Constants.COLD, rate = COLD_SPAWN_RATE),
        COLDER(level = Constants.COLDER, rate = COLDER_SPAWN_RATE),
        COLDEST(level = Constants.COLDEST, rate = COLDEST_SPAWN_RATE)
    }
}


