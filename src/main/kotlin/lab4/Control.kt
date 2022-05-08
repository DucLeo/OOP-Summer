package lab4

class Control (private val model: Model) {
    init {
        startGame()
    }

    private fun startGame() {
        while (model.state == State.MOVE) {
            val input = readln()
            try {
                model.doMove(input.lowercase())
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}