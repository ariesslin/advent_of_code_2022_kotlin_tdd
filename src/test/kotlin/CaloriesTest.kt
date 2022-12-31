import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CaloriesTest {

    private val rawCalories: List<Int?> = listOf(
        1000,
        2000,
        3000,
        null,
        4000,
        null,
        5000,
        6000,
        null,
        7000,
        8000,
        9000,
        null,
        10000,
        null
    )

    @Test
    internal fun day01TestGetWhichElfGetMaxCalories() {
        //arrange
        val allElfCalories = Calories(rawCalories)

        //act
        val (elf, elfCalories) = allElfCalories.getMaxCalories()

        //assert
        assertEquals(elf, 4)
        assertEquals(elfCalories, 24000)
    }

    @Test
    internal fun day01TestGetTopThreeElvesWithMaxCalories() {
        //arrange
        val allElfCalories = Calories(rawCalories)

        //act
        val topThreeElves = allElfCalories.getTopThreeMaxCalories()
        val topThreeCaloriesSum = allElfCalories.getCaloriesSum(topThreeElves)

        //assert
        assertEquals(topThreeElves.elementAt(0).first, 4)
        assertEquals(topThreeElves.elementAt(0).second, 24000)

        assertEquals(topThreeElves.elementAt(1).first, 3)
        assertEquals(topThreeElves.elementAt(1).second, 11000)

        assertEquals(topThreeElves.elementAt(2).first, 5)
        assertEquals(topThreeElves.elementAt(2).second, 10000)

        assertEquals(topThreeCaloriesSum, 45000)
    }
}