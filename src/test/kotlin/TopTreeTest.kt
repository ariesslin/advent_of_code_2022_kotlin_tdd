import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TopTreeTest {
    private val forest: List<String> = listOf(
        "30373",
        "25512",
        "65332",
        "33549",
        "35390"
    )

    @Test
    internal fun day08TestGetVisibleTreeNum() {
        //arrange
        val trees = TopTree(forest)

        //act
        val visibleTreeNum = trees.getVisibleTreeNum()

        //assert
        assertEquals(visibleTreeNum, 21)
    }

    @Test
    internal fun day08TestGetHighestSceneScore() {
        //arrange
        val trees = TopTree(forest)

        //act
        val highestSceneScore = trees.getHighestSceneScore()

        //assert
        assertEquals(highestSceneScore, 8)
    }
}