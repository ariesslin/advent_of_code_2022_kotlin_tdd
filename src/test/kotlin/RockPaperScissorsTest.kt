import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RockPaperScissorsTest {
    private val rawCombatInput: List<String> = listOf(
        "A Y",
        "B X",
        "C Z"
    )

    @Test
    internal fun day02TestGetTotalScoreOfRockPaperScissorsCombat() {
        //arrange
        val combat = RockPaperScissors(rawCombatInput)

        //act
        val totalScore = combat.calTotalScoreOfAllRounds()

        //assert
        assertEquals(totalScore, 15)
    }
}