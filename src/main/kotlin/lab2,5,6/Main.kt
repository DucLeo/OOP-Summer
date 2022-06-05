package `lab2,5,6`

fun main() {
    val shapeCollector = ShapeCollector<ColoredShape2d>()

    shapeCollector.addShape(Circle(5.0, Blue, Red))
    shapeCollector.addShape(Square(5.0, Blue, Orange))
    shapeCollector.addShape(Rectangle(2.0, 2.5, Pink, Red))
    shapeCollector.addShape(Triangle(3.0, 4.0, 5.0, Red, Blue))
    shapeCollector.addShape(Circle(10.0, Gray, Blue))
    shapeCollector.addShape(Rectangle(5.0, 4.0, Violet, Yellow))


    println("There are ${shapeCollector.numberShapes()} shapes in collection, shapes sorted by area in collection:")
    for(temp in shapeCollector.getSorted(compareBy { it.calcArea() })) println("$temp")

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

    val otherShapes = listOf(
        Triangle(6.0, 8.0, 10.0, Gray, Orange),
        Square(10.0, White, Green),
        Circle(6.0, Red, Yellow),
        Rectangle(7.0, 2.0, Violet, Pink)
    )
    shapeCollector.addAll(otherShapes)

    println("\nAfter adding there are ${shapeCollector.numberShapes()} shapes in collection:")
    for(temp in shapeCollector.allStoredShapes()) println("$temp")

    val file = FileIO()
    file.outputToFile(shapeCollector.listShape, "file1")

    val newShapeCollector = ShapeCollector<ColoredShape2d>()
    newShapeCollector.listShape = file.inputFromFile("file1").toMutableList()
    newShapeCollector.addShape(Square(29.0, Blue, Red))
    newShapeCollector.addShape(Triangle(6.0, 8.0,10.0, Blue, Green))
    file.outputToFile(newShapeCollector.listShape, "file2")
}