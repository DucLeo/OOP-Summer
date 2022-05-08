package lab4

enum class Position (private val textValue: String) {
    WALL("#"),
    WAY("."),
    PLAYER("P"),
    EXIT("E");

    override fun toString(): String {
        return textValue
    }
}