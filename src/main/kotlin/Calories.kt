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
