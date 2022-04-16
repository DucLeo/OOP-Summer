package lab2

import kotlin.math.PI
import kotlin.math.sqrt

data class Circle(
    val r: Double,
    override val borderColor: Color,
    override val fillColor: Color
) : ColoredShape2d {
    init {
        if (r <= 0) error("Radius of circle must be a positive")
    }

    override fun calcArea(): Double {
        return PI * r * r
    }

    override fun toString(): String {
        return "Circle($r, $borderColor, $fillColor)"
    }
}

data class Square(
    val a: Double,
    override val borderColor: Color,
    override val fillColor: Color
) : ColoredShape2d {
    init {
        if (a <= 0) error("Side of square must be a positive")
    }

    override fun calcArea(): Double {
        return a * a
    }

    override fun toString(): String {
        return "Square($a, $borderColor, $fillColor)"
    }
}

data class Rectangle(
    val a: Double, val b: Double,
    override val borderColor: Color,
    override val fillColor: Color
) : ColoredShape2d {

    init {
        if (a <= 0) error("Side of rectangle must be a positive")
        if (b <= 0) error("Side of rectangle must be a positive")
        if (a == b) error("Two sides of the rectangle must be different")
    }

    override fun calcArea(): Double {
        return a * b
    }

    override fun toString(): String {
        return "Rectangle ($a, $b, $borderColor, $fillColor)"
    }
}

data class Triangle(
    val a: Double, val b: Double, val c: Double,
    override val borderColor: Color,
    override val fillColor: Color
) : ColoredShape2d {

    init {
        if (a <= 0) error("Side of triangle must be a positive") else a
        if (b <= 0) error("Side of triangle must be a positive") else b
        if (c <= 0) error("Side of triangle must be a positive") else c
        if (a + b <= c || b + c <= a || a + c <= b) error("No triangle with these three sides")
    }

    override fun calcArea(): Double {
        return 0.25 * sqrt((a + b + c) * (a + b - c) * (a - b + c) * (b - a + c))
    }

    override fun toString(): String {
        return "Triangle ($a, $b, $c, $borderColor, $fillColor)"
    }
}