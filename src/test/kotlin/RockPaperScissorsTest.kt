import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RockPaperScissorsTest {
    private val rawCombatInput: List<String> = listOf(
        "A Y",
        "B X",
        "C Z"
    )

    @Test
    internal fun day02TestGetTotalScoreOfRockPaperScissorsCombatIfXYZAsShape() {
        //arrange
        val combat = RockPaperScissors(rawCombatInput)

        //act
        val totalScore = combat.calTotalScoreOfAllRounds(XYZMode.Shape)

        //assert
        assertEquals(totalScore, 15)
    }

    @Test
    internal fun day02TestGetTotalScoreOfRockPaperScissorsCombatIfXYZAsOutcome() {
        //arrange
        val combat = RockPaperScissors(rawCombatInput)

        //act
        val totalScore = combat.calTotalScoreOfAllRounds(XYZMode.Outcome)

        //assert
        assertEquals(totalScore, 12)
    }
}