package lab4

class Console (private val model: Model) {
    init {
        println("Welcome to maze!\n" +
                "Move to exit E :\n"+
                "Press 'a + Enter' - Move left\n" +
                "      'd + Enter' - Move right\n" +
                "      'w + Enter' - Move up\n" +
                "      's + Enter' - Move down\n"+
                "      'q + Enter' - Quit game and save\n")

        val listener = object : ModelChangeListener {
            override fun onModelChanged() {
                repaint()
            }
        }
        model.addModelChangeListener(listener)

        repaint()
    }

    private fun repaint() {
        println(model)
    }
}