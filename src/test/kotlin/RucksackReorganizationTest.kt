import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RucksackReorganizationTest {
    private val rucksackList: List<String> = listOf(
        "vJrwpWtwJgWrhcsFMMfFFhFp",
        "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
        "PmmdzqPrVvPwwTWBwg",
        "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
        "ttgJtRGJQctTZtZT",
        "CrZsJsPPZsGzwwsLwLmpwMDw"
    )

    @Test
    internal fun day03TestGetSumOfPrioritiesOfAllItemTypes() {
        //arrange
        val rucksacks = RucksackReorganization(rucksackList)

        //act
        val rucksackPriorities = rucksacks.getRucksackPriorities()
        val rucksackPrioritiesSum = rucksacks.getRucksackPrioritiesSum(rucksackPriorities)

        //assert
        assertEquals(rucksackPriorities[0], 16)
        assertEquals(rucksackPriorities[1], 38)
        assertEquals(rucksackPriorities[2], 42)
        assertEquals(rucksackPriorities[3], 22)
        assertEquals(rucksackPriorities[4], 20)
        assertEquals(rucksackPriorities[5], 19)

        assertEquals(rucksackPrioritiesSum, 157)

    }
}