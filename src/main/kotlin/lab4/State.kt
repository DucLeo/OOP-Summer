package lab4

enum class State (private val textValue: String) {
    MOVE("Which next direction?"),
    QUIT("Your position has been saved, continue your journey the next time you play!"),
    WIN("Amazing, good job! You have escaped the maze!");

    override fun toString(): String {
        return textValue
    }
}