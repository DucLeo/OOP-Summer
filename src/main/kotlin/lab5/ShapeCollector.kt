package lab5

import lab2.Color
import lab2.ColoredShape2d
import lab2.Shape2d

class ShapeCollector<T: ColoredShape2d> {
    var listShape: MutableList<T> = mutableListOf()

    fun addShape(Shape: T) {
        listShape.add(Shape)
    }

    fun smallestShape(): Shape2d? {
        return if (listShape.isEmpty()) {
            null
        } else {
            var smallestShape = listShape[0]
            for (temp in listShape) {
                if (temp.calcArea() < smallestShape.calcArea()) {
                    smallestShape = temp
                }
            }
            smallestShape
        }
    }

    fun largestShape(): Shape2d? {
        return if (listShape.isEmpty()) {
            null
        } else {
            var largestShape = listShape[0]
            for (temp in listShape) {
                if (temp.calcArea() > largestShape.calcArea()) {
                    largestShape = temp
                }
            }
            largestShape
        }
    }

    fun sumArea(): Double {
        if (listShape.isEmpty()) throw IllegalArgumentException("Collector shapes is empty!")
        else {
            var sumArea = 0.0
            for (temp in listShape) {
                sumArea += temp.calcArea()
            }
            return sumArea
        }
    }

    fun shapeBorderColor(Color: Color): List<T> {
        val searchList: MutableList<T> = mutableListOf()
        if (listShape.isNotEmpty()) {
            for (temp in listShape) {
                if (temp.borderColor == Color)
                    searchList.add(temp)
            }
        }
        return searchList
    }

    fun shapeFillColor(Color: Color): List<T> {
        val searchList: MutableList<T> = mutableListOf()
        if (listShape.isNotEmpty()) {
            for (temp in listShape) {
                if (temp.fillColor == Color)
                    searchList.add(temp)
            }
        }
        return searchList
    }

    fun allStoredShapes(): List<T> {
        return listShape.toList()
    }

    fun numberShapes(): Int {
        return listShape.size
    }

    fun getShapesGroupedByBorderColor(): Map<Color, List<T>> {
        if (listShape.isEmpty()) throw IllegalArgumentException("Collector shapes is empty!")
        else {
            val map = mutableMapOf<Color, List<T>>()
            for (shape in listShape) {
                map[shape.borderColor] = shapeBorderColor(shape.borderColor)
            }
            return map
        }
    }

    fun getShapesGroupedByFillColor(): Map<Color, List<T>> {
        if (listShape.isEmpty()) throw IllegalArgumentException("Collector shapes is empty!")
        else {
            val map = mutableMapOf<Color, List<T>>()
            for (shape in listShape) {
                map[shape.fillColor] = shapeFillColor(shape.fillColor)
            }
            return map
        }
    }

    inline fun <reified Type> shapesByType(): List<T> {
        val newList = mutableListOf<T>()
        if (listShape.isNotEmpty()) {
            for (Shape in listShape) {
                if (Shape is Type)
                    newList.add(Shape)
            }
        }
        return newList
    }

    fun addAll(otherShapeCollector: ShapeCollector<T>) {
        listShape += otherShapeCollector.allStoredShapes()
    }

    fun getSorted(comparator: Comparator<T>): List<T> {
        return listShape.sortedWith(comparator)
    }
}