import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SupplyStacksTest {
    private val stacksInput: List<String> = listOf(
        "    [D]",
        "[N] [C]",
        "[Z] [M] [P]",
        " 1   2   3",
        "",
        "move 1 from 2 to 1",
        "move 3 from 1 to 3",
        "move 2 from 2 to 1",
        "move 1 from 1 to 2"
    )

    @Test
    internal fun day05TestGetInitialStacks() {
        //arrange
        val supplyStacks = SupplyStacks(stacksInput)

        //act
        val stacks = supplyStacks.parseInitialStacksFromRawInput()

        //assert
        assertEquals(stacks.size, 3)
        assertEquals(stacks[0], ArrayDeque(listOf('Z', 'N')))
        assertEquals(stacks[1], ArrayDeque(listOf('M', 'C', 'D')))
        assertEquals(stacks[2], ArrayDeque(listOf('P')))
    }

    @Test
    internal fun day05TestGetInitialMoves() {
        //arrange
        val supplyStacks = SupplyStacks(stacksInput)

        //act
        val moves = supplyStacks.parseMovesFromRawInput()

        //assert
        assertEquals(moves.size, 4)
        assertEquals(moves[0], Triple(1, 2, 1))
        assertEquals(moves[1], Triple(3, 1, 3))
        assertEquals(moves[2], Triple(2, 2, 1))
        assertEquals(moves[3], Triple(1, 1, 2))
    }

    @Test
    internal fun day05TestGetSupplyStackMovesResult() {
        //arrange
        val supplyStacks = SupplyStacks(stacksInput)

        //act
        val result = supplyStacks.getSupplyStackMovesResult()

        //assert
        assertEquals(result, "CMZ")
    }
}