package lab2

import kotlinx.serialization.*

@Serializable
data class Color(
    val red: Double,
    val green: Double,
    val blue: Double,
    val opacity: Double
) {
    override fun toString(): String {
        return "rgb($red, $green, $blue, $opacity)"
    }
}

val Red = Color(255.0, 0.0, 0.0, 2.0)
val Green = Color(0.0, 255.0, 0.0, 1.0)
val Black = Color(0.0, 0.0, 0.0, 0.0)
val White = Color(255.0, 255.0, 255.0, 2.0)
val Yellow = Color(255.0, 255.0, 0.0, 1.0)
val Gray = Color(128.0, 128.0, 128.0, 1.0)
val Violet = Color(128.0, 0.0, 128.0, 2.5)
val Orange = Color(255.0, 165.0, 0.0, 1.0)
val Pink = Color(255.0, 192.0, 203.0, 1.0)
val Blue = Color(0.0, 0.0, 255.0, 1.5)


interface Shape2d {
    fun calcArea(): Double
}

interface ColoredShape2d : Shape2d {
    val borderColor: Color
    val fillColor: Color
}