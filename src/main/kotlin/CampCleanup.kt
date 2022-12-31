class CampCleanup(_assignments: List<String>) {
    private val assignments = _assignments

    private fun isFullOverlapping(a: Pair<Int, Int>, b: Pair<Int, Int>): Boolean {
        if (a.first <= b.first && a.second >= b.second) {
            return true
        }

        if (a.first >= b.first && a.second <= b.second) {
            return true
        }

        return false
    }

    private fun isAtLeastOneOverlapping(a: Pair<Int, Int>, b: Pair<Int, Int>): Boolean {
        if (a.first > b.second || a.second < b.first) {
            return false
        }

        return true
    }

    private fun parseRawStringToIntPairs(input: String): List<Pair<Int, Int>> {
        val parts = input.split(',', '-').map { it.toInt() }

        return listOf(Pair(parts[0], parts[1]), Pair(parts[2], parts[3]))
    }

    private fun getNumOfOverlapping(checkOverlappingMode: (a: Pair<Int, Int>, b: Pair<Int, Int>) -> Boolean): Int {
        var num = 0
        for (assignment in assignments) {
            val (a, b) = parseRawStringToIntPairs(assignment)
            if (checkOverlappingMode(a, b)) {
                num += 1
            }
        }
        return num
    }

    fun getNumOfFullOverlapping(): Int {
        return getNumOfOverlapping(::isFullOverlapping)
    }

    fun getNumOfAtLeastOneOverlapping(): Int {
        return getNumOfOverlapping(::isAtLeastOneOverlapping)
    }
}