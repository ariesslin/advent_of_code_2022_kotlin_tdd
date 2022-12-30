import utils.readFileAsLinesUsingUseLines

fun main(args: Array<String>) {
    day01()
}

fun day01() {
    val rawCalories = readFileAsLinesUsingUseLines("/day01_input.txt")
    val allElfCalories = Calories(rawCalories)
    val (elf, elfCalories) = allElfCalories.getMaxCalories()
    println("Elf $elf is with MAX calories $elfCalories")
    
    val topThreeElves = allElfCalories.getTopThreeMaxCalories()
    val topThreeCaloriesSum = allElfCalories.getCaloriesSum(topThreeElves)
    println("Top three elves with MAX calories get the sum $topThreeCaloriesSum")
}

class Calories(_rawCalories: List<Int?>) {
    private val rawCalories = _rawCalories
    private val elfCaloriesCollection = mutableListOf<Pair<Int, Int>>()

    private fun genElfCaloriesMap() {
        var elf = 1
        var calories = 0
        for (r in rawCalories) {
            if (r != null) {
                calories += r
            } else {
                elfCaloriesCollection.add(Pair(elf, calories))
                elf += 1
                calories = 0
            }
        }
    }

    fun getMaxCalories(): Pair<Int, Int> {
        if (rawCalories.isEmpty()) {
            return Pair(0, 0)
        }

        if (elfCaloriesCollection.isEmpty()) {
            genElfCaloriesMap()
        }

        return elfCaloriesCollection.maxBy { it.second }
    }

    fun getTopThreeMaxCalories(): List<Pair<Int, Int>> {
        if (rawCalories.isEmpty()) {
            return emptyList()
        }

        if (elfCaloriesCollection.isEmpty()) {
            genElfCaloriesMap()
        }

        // TODO: magic number?
        return elfCaloriesCollection.sortedByDescending { it.second }.take(3)
    }

    fun getCaloriesSum(calories: List<Pair<Int, Int>>): Int {
        var sum = 0
        for (c in calories) {
            sum += c.second
        }
        return sum
    }

}
