package lab6

import lab2.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.modules.*

import java.io.File

object JsonSerialization {
    private val json = Json {
        serializersModule = SerializersModule {
            polymorphic(ColoredShape2d::class) {
                subclass(Circle::class)
                subclass(Square::class)
                subclass(Rectangle::class)
                subclass(Triangle::class)
            }
        }
    }

    fun serialization(listShape: List<ColoredShape2d>): String = json.encodeToString(listShape)

    fun deserialization(stringToDecode: String): List<ColoredShape2d> = json.decodeFromString(stringToDecode)
}

class FileIO {
    fun inputFromFile(inputFile: String): List<ColoredShape2d>  {
        return JsonSerialization.deserialization(File(inputFile).readText())
    }

    fun outputToFile(listShape: List<ColoredShape2d>, outputFile: String) {
        File(outputFile).writeText(JsonSerialization.serialization(listShape))
    }
}