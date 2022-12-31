class CampCleanup(_assignments: List<String>) {
    private val assignments = _assignments

    private fun isFullyOverlapping(a: Pair<Int, Int>, b: Pair<Int, Int>): Boolean {
        if (a.first <= b.first && a.second >= b.second) {
            return true
        }

        if (a.first >= b.first && a.second <= b.second) {
            return true
        }

        return false
    }

    private fun parseRawStringToIntPairs(input: String): List<Pair<Int, Int>> {
        val parts = input.split(',', '-').map { it.toInt() }

        return listOf(Pair(parts[0], parts[1]), Pair(parts[2], parts[3]))
    }

    fun getNumOfFullyOverlapping(): Int {
        var num = 0
        for (assignment in assignments) {
            val (a, b) = parseRawStringToIntPairs(assignment)
            if (isFullyOverlapping(a, b)) {
                num += 1
            }
        }
        return num
    }


}