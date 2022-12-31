import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CampCleanupTest {
    private val assignments: List<String> = listOf(
        "2-4,6-8",
        "2-3,4-5",
        "5-7,7-9",
        "2-8,3-7",
        "6-6,4-6",
        "2-6,4-8"
    )

    @Test
    internal fun day04TestGetWhichPairsHaveFullyOverlapping() {
        //arrange
        val pairs = CampCleanup(assignments)

        //act
        val numOfFullOverlapping = pairs.getNumOfFullyOverlapping()

        //assert
        assertEquals(numOfFullOverlapping, 2)
    }
}