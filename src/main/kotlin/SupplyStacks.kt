const val MOVE = "move"
const val FROM = "from"
const val TO = "to"

class SupplyStacks(_stacksInput: List<String>) {
    private val stacksInput = _stacksInput
    private var stackBaseSize: Int = 0

    fun parseInitialStacksFromRawInput(): List<ArrayDeque<Char>> {
        val stackBaseIndex = stacksInput.indexOfFirst { !it.contains('[') }
        val stackBaseList = stacksInput[stackBaseIndex].trim().split("\\s+".toRegex()).map { it.toInt() }
        stackBaseSize = stackBaseList.size

        val stacks: List<ArrayDeque<Char>> = List(stackBaseSize) { ArrayDeque() }

        for (i in stackBaseIndex - 1 downTo 0) {
            for (j in 0 until stackBaseSize) {
                val pos = 4 * j + 1
                if (pos < stacksInput[i].length && stacksInput[i][pos] != ' ') {
                    stacks[j].addLast(stacksInput[i][pos])
                }
            }
        }

        return stacks
    }

    fun parseMovesFromRawInput(): List<Triple<Int, Int, Int>> {
        val firstMoveIndex = stacksInput.indexOfFirst { it.contains("move") }
        val moves = mutableListOf<Triple<Int, Int, Int>>()

        for (i in firstMoveIndex until stacksInput.size) {
            val moveStart = stacksInput[i].indexOf(MOVE)
            val moveEnd = moveStart + MOVE.length

            val fromStart = stacksInput[i].indexOf(FROM)
            val fromEnd = fromStart + FROM.length

            val toStart = stacksInput[i].indexOf(TO)
            val toEnd = toStart + TO.length

            val move = stacksInput[i].slice(moveEnd until fromStart).trim().toInt()
            val from = stacksInput[i].slice(fromEnd until toStart).trim().toInt()
            val to = stacksInput[i].slice(toEnd until stacksInput[i].length).trim().toInt()

            moves.add(Triple(move, from, to))
        }

        return moves
    }

    fun getSupplyStackMovesResult(): String {
        val stacks = parseInitialStacksFromRawInput()
        val moves = parseMovesFromRawInput()

        for (m in moves) {
            for (i in 0 until m.first) {
                val value = stacks[m.second - 1].removeLast()
                stacks[m.third - 1].addLast(value)
            }
        }

        var str = ""

        for (s in stacks) {
            str += s.last()
        }

        return str
    }
}