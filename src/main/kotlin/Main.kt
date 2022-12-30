import utils.readFileAsLinesToListInt
import utils.readFileAsLinesToListString

fun main(args: Array<String>) {
    day01()
    day02()
}

fun day01() {
    val rawCalories = readFileAsLinesToListInt("/day01_input.txt")
    val allElfCalories = Calories(rawCalories)
    val (elf, elfCalories) = allElfCalories.getMaxCalories()
    println("Elf $elf is with MAX calories $elfCalories")

    val topThreeElves = allElfCalories.getTopThreeMaxCalories()
    val topThreeCaloriesSum = allElfCalories.getCaloriesSum(topThreeElves)
    println("Top three elves with MAX calories get the sum $topThreeCaloriesSum")
}

fun day02() {
    val rawCombatInput = readFileAsLinesToListString("/day02_input.txt")
    val combat = RockPaperScissors(rawCombatInput)
    val totalScoreIfXYZAsShape = combat.calTotalScoreOfAllRounds(XYZMode.Shape)
    println("Rock paper scissors (shape mode) total score is $totalScoreIfXYZAsShape")

    val totalScoreIfXYZAsOutcome = combat.calTotalScoreOfAllRounds(XYZMode.Outcome)
    println("Rock paper scissors (outcome mode) total score is $totalScoreIfXYZAsOutcome")
}