import utils.readFileAsLinesToListInt
import utils.readFileAsLinesToListString

fun main() {
    day01()
    day02()
    day03()
    day04()
    day05()
    day06()
    day07()
    day08()
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

fun day03() {
    val rucksackList = readFileAsLinesToListString("/day03_input.txt")
    val rucksacks = RucksackReorganization(rucksackList)
    val rucksackPriorities = rucksacks.getRucksackPriorities()
    val rucksackPrioritiesSum = rucksacks.getPrioritiesSum(rucksackPriorities)
    println("The sum of rucksack priorities is $rucksackPrioritiesSum")

    val groupBadgePriorities = rucksacks.getGroupBadgePriorities()
    val groupBadgePrioritiesSum = rucksacks.getPrioritiesSum(groupBadgePriorities)
    println("The sum of group badge priorities is $groupBadgePrioritiesSum")
}

fun day04() {
    val assignments = readFileAsLinesToListString("/day04_input.txt")
    val pairs = CampCleanup(assignments)
    val numOfFullOverlapping = pairs.getNumOfFullOverlapping()
    println("Num of fully overlapping pairs is $numOfFullOverlapping")

    val numOfAtLeastOneOverlapping = pairs.getNumOfAtLeastOneOverlapping()
    println("Num of at least one overlapping pairs is $numOfAtLeastOneOverlapping")
}

fun day05() {
    val stacksInput = readFileAsLinesToListString("/day05_input.txt")
    val supplyStacks = SupplyStacks(stacksInput)
    val result = supplyStacks.getSupplyStackMovesResult()
    println("After all moves, the supply stack top is $result")

    val resultWithPowerfulCrate = supplyStacks.getSupplyStackMovesResultWithPowerfulCrate()
    println("With Powerful Crate, after all moves, the supply stack top is $resultWithPowerfulCrate")
}

fun day06() {
    val rawDataStreamsInput = readFileAsLinesToListString("/day06_input.txt")
    val dataStreams = TuningTrouble(rawDataStreamsInput)
    val markerPosWithWindowSizeIs4 = dataStreams.getFirstMarkerPosition(windowSize = 4)
    println("The first valid marker position is at $markerPosWithWindowSizeIs4")

    val markerPosWithWindowSizeIs14 = dataStreams.getFirstMarkerPosition(windowSize = 14)
    println("The first valid marker position is at $markerPosWithWindowSizeIs14")
}

fun day07() {
    val fileTreeRawInput = readFileAsLinesToListString("/day07_input.txt")
    val spaceUsage = SpaceOnDevice(fileTreeRawInput)
    val limit = 100000
    val dirSizeSum = spaceUsage.getDirSizeSum(limit)
    println("The sum of dir size under limit $limit is $dirSizeSum")

    val dir = spaceUsage.getDirToDelete()
    println("The dir to delete is " + dir.first + " size is " + dir.second)
}

fun day08() {
    val forest = readFileAsLinesToListString("/day08_input.txt")
    val trees = TopTree(forest)
    val visibleTreeNum = trees.getVisibleTreeNum()
    println("The visible tree number is $visibleTreeNum")
}