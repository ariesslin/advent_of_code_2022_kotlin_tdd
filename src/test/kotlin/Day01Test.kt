import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day01Test {

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
        10000
    )

    @Test
    internal fun testWhichElfGetMaxCalories() {

        //arrange
        val allElfCalories = Calories(rawCalories)

        //act
        val (elf, elfCalories) = allElfCalories.getMaxCalories()

        //assert
        assertEquals(elf, 4)
        assertEquals(elfCalories, 24000)
    }
}