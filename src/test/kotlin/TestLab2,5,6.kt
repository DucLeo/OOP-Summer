import `lab2,5,6`.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import kotlin.math.PI

val Circle1: Circle = Circle(5.0, Black, White)
val Circle2: Circle = Circle(10.0, Black, Yellow)
val Square1 = Square(5.0, Green, Yellow)
val Square2: Square = Square(10.0, Orange, Blue)
val Rectangle1: Rectangle = Rectangle(5.0, 10.0, Gray, Red)
val Rectangle2: Rectangle = Rectangle(10.0, 5.0, Pink, White)
val Triangle1: Triangle = Triangle(3.0, 4.0, 5.0, Violet, Blue)
val Triangle2: Triangle = Triangle(6.0, 8.0, 10.0, Orange, Green)

internal class ShapeCollectorTest {
    @Test
    fun testAddShape() {
        val collector = ShapeCollector<ColoredShape2d>()
        assertEquals(collector.listShape.toList(), emptyList<ColoredShape2d>())

        collector.addShape(Square1)
        assertEquals(collector.listShape[0], Square1)

    }

    @Test
    fun testSmallestShape() {
        val collector = ShapeCollector<ColoredShape2d>()
        assertEquals(collector.smallestShape(), null)

        collector.addShape(Circle1)
        collector.addShape(Square1)
        collector.addShape(Rectangle1)
        collector.addShape(Triangle1)

        assertEquals(collector.smallestShape(), collector.listShape.minByOrNull { it.calcArea() })
    }

    @Test
    fun testLargestShape() {
        val collector = ShapeCollector<ColoredShape2d>()
        assertEquals(collector.largestShape(), null)

        collector.addShape(Circle2)
        collector.addShape(Square2)
        collector.addShape(Rectangle2)
        collector.addShape(Triangle2)

        assertEquals(collector.largestShape(), collector.listShape.maxByOrNull { it.calcArea() })
    }

    @Test
    fun testSumArea() {
        val collector = ShapeCollector<ColoredShape2d>()
        assertThrows<IllegalArgumentException> {
            collector.sumArea()
        }
        collector.addShape(Circle1)
        collector.addShape(Square1)
        collector.addShape(Rectangle2)
        collector.addShape(Triangle2)

        assertEquals(collector.sumArea(), 5 * 5 * PI + 5 * 5 + 10 * 5 + 0.5 * 6 * 8)
    }

    @Test
    fun testShapeBorderColor() {
        val collector = ShapeCollector<ColoredShape2d>()
        assertEquals(collector.shapeBorderColor(Black), emptyList<ColoredShape2d>())

        collector.addShape(Circle1)
        collector.addShape(Square1)
        collector.addShape(Rectangle1)
        collector.addShape(Triangle1)

        assertEquals(collector.shapeBorderColor(Black), collector.listShape.filter { it.borderColor == Black })
    }

    @Test
    fun testShapeFillColor() {
        val collector = ShapeCollector<ColoredShape2d>()
        assertEquals(collector.shapeFillColor(White), emptyList<ColoredShape2d>())

        collector.addShape(Circle2)
        collector.addShape(Square2)
        collector.addShape(Rectangle2)
        collector.addShape(Triangle2)

        assertEquals(collector.shapeFillColor(White), collector.listShape.filter { it.fillColor == White })
    }

    @Test
    fun testAllStoredShapes() {
        val collector = ShapeCollector<ColoredShape2d>()
        assertSame(collector.allStoredShapes(), emptyList<ColoredShape2d>())

        collector.addShape(Circle1)
        collector.addShape(Square1)
        collector.addShape(Rectangle2)
        collector.addShape(Triangle2)

        assertEquals(collector.allStoredShapes(), listOf(Circle1, Square1, Rectangle2, Triangle2))
    }

    @Test
    fun testNumberShapes() {
        val collector = ShapeCollector<ColoredShape2d>()
        assertEquals(collector.numberShapes(), 0)

        collector.addShape(Circle1)
        collector.addShape(Square1)
        collector.addShape(Rectangle1)
        collector.addShape(Triangle1)

        assertEquals(collector.numberShapes(), 4)
    }

    @Test
    fun testGetShapesGroupedByBorderColor() {
        val collector = ShapeCollector<ColoredShape2d>()
        assertThrows<IllegalArgumentException> {
            collector.getShapesGroupedByBorderColor()
        }

        collector.addShape(Circle2)
        collector.addShape(Square2)
        collector.addShape(Rectangle2)
        collector.addShape(Triangle2)

        assertEquals(collector.getShapesGroupedByBorderColor(), collector.listShape.groupBy { it.borderColor })
    }

    @Test
    fun testGetShapesGroupedByFillColor() {
        val collector = ShapeCollector<ColoredShape2d>()
        assertThrows<IllegalArgumentException> {
            collector.getShapesGroupedByFillColor()
        }

        collector.addShape(Circle1)
        collector.addShape(Square1)
        collector.addShape(Rectangle2)
        collector.addShape(Triangle2)

        assertEquals(collector.getShapesGroupedByFillColor(), collector.listShape.groupBy { it.fillColor })
    }

    @Test
    fun testShapesByType() {
        val collector = ShapeCollector<ColoredShape2d>()
        assertEquals(collector.shapesByType<Square>(), emptyList<ColoredShape2d>())

        collector.addShape(Circle1)
        collector.addShape(Square1)
        collector.addShape(Rectangle1)
        collector.addShape(Triangle1)

        assertEquals(collector.shapesByType<Square>(), collector.listShape.filterIsInstance<Square>())
    }

    @Test
    fun testAddAll(){
        val collector = ShapeCollector<ColoredShape2d>()
        collector.addShape(Circle1)
        collector.addShape(Square1)

        val otherShapes = listOf(
            Rectangle2,
            Triangle2
        )

        collector.addAll(otherShapes)
        assertEquals(collector.allStoredShapes(), listOf(Circle1, Square1, Rectangle2, Triangle2))
    }

    @Test
    fun testGetSorted(){
        val collector = ShapeCollector<ColoredShape2d>()

        collector.addShape(Circle1)
        collector.addShape(Square1)
        collector.addShape(Circle2)
        collector.addShape(Square2)

        assertEquals(collector.getSorted(compareBy { it.calcArea() }), listOf(Square1, Circle1, Square2, Circle2))
    }

    @Test
    fun testSerialization() {
        val collector = ShapeCollector<ColoredShape2d>()
        collector.addShape(Circle1)
        collector.addShape(Square2)
        val expected = "[{\"type\":\"lab2,5,6.Circle\",\"r\":5.0,\"borderColor\":{\"red\":0.0,\"green\":0.0,\"blue\":0.0,\"opacity\":0.0},\"fillColor\":{\"red\":255.0,\"green\":255.0,\"blue\":255.0,\"opacity\":2.0}},{\"type\":\"lab2,5,6.Square\",\"a\":10.0,\"borderColor\":{\"red\":255.0,\"green\":165.0,\"blue\":0.0,\"opacity\":1.0},\"fillColor\":{\"red\":0.0,\"green\":0.0,\"blue\":255.0,\"opacity\":1.5}}]"
        assertEquals(JsonSerialization.serialization(collector.listShape), expected)
    }

    @Test
    fun testDeserialization() {
        val stringToDecode = "[{\"type\":\"lab2,5,6.Circle\",\"r\":5.0,\"borderColor\":{\"red\":0.0,\"green\":0.0,\"blue\":0.0,\"opacity\":0.0},\"fillColor\":{\"red\":255.0,\"green\":255.0,\"blue\":255.0,\"opacity\":2.0}},{\"type\":\"lab2,5,6.Square\",\"a\":10.0,\"borderColor\":{\"red\":255.0,\"green\":165.0,\"blue\":0.0,\"opacity\":1.0},\"fillColor\":{\"red\":0.0,\"green\":0.0,\"blue\":255.0,\"opacity\":1.5}}]"
        val listShape = JsonSerialization.deserialization(stringToDecode)
        assertEquals(listShape[0], Circle(5.0, Black, White))
        assertEquals(listShape[1], Square(10.0, Orange, Blue))
    }
}