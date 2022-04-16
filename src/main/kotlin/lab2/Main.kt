package lab2

fun main() {

    val shapeCollector = ShapeCollector()

    shapeCollector.addShape(Circle(5.0, Blue, Red))
    shapeCollector.addShape(Square(5.0, Blue, Orange))
    shapeCollector.addShape(Rectangle(2.0, 2.5, Pink, Red))
    shapeCollector.addShape(Triangle(3.0, 4.0, 5.0, Red, Blue))
    shapeCollector.addShape(Circle(10.0, Gray, Blue))
    shapeCollector.addShape(Rectangle(5.0, 4.0, Violet, Yellow))


    println("There are ${shapeCollector.numberShapes()} shapes in collection:")

    for(temp in shapeCollector.allStoredShapes()) println("$temp")

    println("\nTotal area of all shape in collection: ${shapeCollector.sumArea()}")

    println("\nSmallest shape (area ${shapeCollector.smallestShape()!!.calcArea()}):\n${shapeCollector.smallestShape()}")

    println("\nLargest shape (area ${shapeCollector.largestShape()!!.calcArea()}):\n${shapeCollector.largestShape()}")

    println("\nAll shapes have border color BLUE:")
    for(temp in shapeCollector.shapeBorderColor(Blue)) println("$temp")

    println("\nAll shapes have fill color RED:")
    for(temp in shapeCollector.shapeFillColor(Red)) println("$temp")

    println("\nShapes grouped by border color:")
    for(temp in shapeCollector.getShapesGroupedByBorderColor()) println("$temp")

    println("\nShapes grouped by fill color:")
    for(temp in shapeCollector.getShapesGroupedByFillColor()) println("$temp")

    println("\nAll circle in collector:")
    for(temp in shapeCollector.shapesByType<Circle>()) println("$temp")
}