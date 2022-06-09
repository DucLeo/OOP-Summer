package `lab2,5,6`

class ShapeCollector<T: ColoredShape2d> {
    var listShape: MutableList<T> = mutableListOf()

    fun addShape(Shape: T) {
        listShape.add(Shape)
    }

    fun smallestShape(): T? {
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

    fun largestShape(): T? {
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

    inline fun <reified Type> shapesByType(): List<Type> {
        val newList = mutableListOf<Type>()
        if (listShape.isNotEmpty()) {
            for (shape in listShape) {
                if (shape is Type)
                    newList.add(shape)
            }
        }
        return newList
    }

    fun addAll(shapes: List<T>) {
        listShape += shapes
    }

    fun getSorted(comparator: Comparator<in T>): List<T> {
        return listShape.sortedWith(comparator)
    }
}