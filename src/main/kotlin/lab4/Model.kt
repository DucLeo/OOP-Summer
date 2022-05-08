package lab4

import java.io.File

class Model {
    private val maze: MutableList<MutableList<Position>> = readMaze()
    private var positionPlayer = positionPlayer()
    private val listeners: MutableSet<ModelChangeListener> = mutableSetOf()
    var state = State.MOVE

    fun addModelChangeListener(listener: ModelChangeListener) {
        listeners.add(listener)
    }

    private fun notifyListeners() {
        listeners.forEach { it.onModelChanged() }
    }

    private fun readMaze(): MutableList<MutableList<Position>> {
        val file = File("C:\\Users\\GodLu\\IdeaProjects\\OOP\\src\\main\\kotlin\\lab4\\save").readLines()
        val maze = MutableList(file.size) {
            MutableList(file[0].length) { Position.WALL }
        }

        for (i in file.indices) {
            if (file[i].length != file[0].length) error("Error size maze!")
            for (j in 0 until file[0].length) {
                when (file[i][j]){
                    '.' -> maze[i][j] = Position.WAY
                    'P' -> maze[i][j] = Position.PLAYER
                    'E' -> maze[i][j] = Position.EXIT
                    '#' -> {}
                    else -> error("Error maze!")
                }
            }
        }
        return maze
    }

    private fun positionPlayer(): Pair<Int, Int> {
        var position = Pair(0, 0)
        for (i in 0 until maze.size) {
            for (j in 0 until maze[0].size) {
                if (maze[i][j] == Position.PLAYER) {
                    position = Pair(i, j)
                }
            }
        }
        return position
    }

    fun doMove(key: String){
        require(state == State.MOVE)

        when (key) {
            "a" -> {
                if (positionPlayer.second == 0) error("You can't move! It's invisible wall.")
                when (maze[positionPlayer.first][positionPlayer.second - 1]) {
                    Position.EXIT -> {
                        maze[positionPlayer.first][positionPlayer.second] = Position.WAY
                        maze[positionPlayer.first][positionPlayer.second - 1] = Position.PLAYER
                        positionPlayer = Pair(positionPlayer.first, positionPlayer.second - 1)
                        state = State.WIN
                    }
                    Position.WAY -> {
                        maze[positionPlayer.first][positionPlayer.second] = Position.WAY
                        maze[positionPlayer.first][positionPlayer.second - 1] = Position.PLAYER
                        positionPlayer = Pair(positionPlayer.first, positionPlayer.second - 1)
                    }
                    Position.WALL -> {
                        error("You can't move! It's wall.")
                    }
                }
            }

            "d" -> {
                if (positionPlayer.second == maze[0].size - 1) error("You can't move! It's invisible wall.")
                when (maze[positionPlayer.first][positionPlayer.second + 1]) {
                    Position.EXIT -> {
                        maze[positionPlayer.first][positionPlayer.second] = Position.WAY
                        maze[positionPlayer.first][positionPlayer.second + 1] = Position.PLAYER
                        positionPlayer = Pair(positionPlayer.first, positionPlayer.second + 1)
                        state = State.WIN
                    }
                    Position.WAY -> {
                        maze[positionPlayer.first][positionPlayer.second] = Position.WAY
                        maze[positionPlayer.first][positionPlayer.second + 1] = Position.PLAYER
                        positionPlayer = Pair(positionPlayer.first, positionPlayer.second + 1)
                    }
                    Position.WALL -> {
                        error("You can't move! It's wall.")
                    }
                }
            }

            "w" -> {
                if (positionPlayer.first == 0) error("You can't move! It's invisible wall.")
                when (maze[positionPlayer.first - 1][positionPlayer.second]) {
                    Position.EXIT -> {
                        maze[positionPlayer.first][positionPlayer.second] = Position.WAY
                        maze[positionPlayer.first - 1][positionPlayer.second] = Position.PLAYER
                        positionPlayer = Pair(positionPlayer.first - 1, positionPlayer.second)
                        state = State.WIN
                    }
                    Position.WAY -> {
                        maze[positionPlayer.first][positionPlayer.second] = Position.WAY
                        maze[positionPlayer.first - 1][positionPlayer.second] = Position.PLAYER
                        positionPlayer = Pair(positionPlayer.first - 1, positionPlayer.second)
                    }
                    Position.WALL -> {
                        error("You can't move! It's wall.")
                    }
                }
            }

            "s" -> {
                if (positionPlayer.first == maze.size - 1) error("You can't move! It's invisible wall.")
                when (maze[positionPlayer.first + 1][positionPlayer.second]) {
                    Position.EXIT -> {
                        maze[positionPlayer.first][positionPlayer.second] = Position.WAY
                        maze[positionPlayer.first + 1][positionPlayer.second] = Position.PLAYER
                        positionPlayer = Pair(positionPlayer.first + 1, positionPlayer.second)
                        state = State.WIN
                    }
                    Position.WAY -> {
                        maze[positionPlayer.first][positionPlayer.second] = Position.WAY
                        maze[positionPlayer.first + 1][positionPlayer.second] = Position.PLAYER
                        positionPlayer = Pair(positionPlayer.first + 1, positionPlayer.second)
                    }
                    Position.WALL -> {
                        error("You can't move! It's wall.")
                    }
                }
            }

            "q" -> {
                saveGame()
                state = State.QUIT
            }

            else -> {
                println("Wrong key?, try again!\n")
            }

        }

        if (state == State.WIN) {
            val fileMaze = File("C:\\Users\\GodLu\\IdeaProjects\\OOP\\src\\main\\kotlin\\lab4\\maze")
            val fileSave = File("C:\\Users\\GodLu\\IdeaProjects\\OOP\\src\\main\\kotlin\\lab4\\save")
            fileMaze.copyTo(fileSave, true)
        }

        notifyListeners()
    }

    private fun saveGame() {
        val file = File("C:\\Users\\GodLu\\IdeaProjects\\OOP\\src\\main\\kotlin\\lab4\\save").bufferedWriter()
        for (i in 0 until maze.size) {
            for (j in 0 until maze[0].size) {
                file.write(maze[i][j].toString())
            }
            file.newLine()
        }
        file.close()
    }

    override fun toString(): String {
        return buildString {
            if (state != State.QUIT) {
                for (i in 0 until maze.size) {
                    for (j in 0 until maze[0].size) {
                        append(maze[i][j])
                    }
                    appendLine()
                }
            }
            appendLine()
            append(state)
        }
    }
}