import utils.readFileAsLinesUsingUseLines

fun main(args: Array<String>) {
    val rawCalories = readFileAsLinesUsingUseLines("/day01_input.txt")
    val allElfCalories = Calories(rawCalories)
    val (elf, elfCalories) = allElfCalories.getMaxCalories()
    println("Elf $elf is with MAX calories $elfCalories")
}

class Calories(_rawCalories: List<Int?>) {
    private val rawCalories = _rawCalories

    fun getMaxCalories() : Pair<Int, Int>  {
        if (rawCalories.isEmpty()) {
            return Pair(0, 0)
        }
        val elfCaloriesMap = mutableMapOf<Int, Int>()
        var elf = 1
        var calories = 0
        for (r in rawCalories) {
            if (r != null) {
                calories += r
            } else {
                elfCaloriesMap.put(elf, calories)
                elf += 1
                calories = 0
            }
        }

        val maxBy = elfCaloriesMap.maxBy { it.value }
        return Pair(maxBy.key, maxBy.value)
    }

}
