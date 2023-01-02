import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SpaceOnDeviceTest {
    private val fileTreeRawInput: List<String> = listOf(
        "$ cd /",
        "$ ls",
        "dir a",
        "14848514 b.txt",
        "8504156 c.dat",
        "dir d",
        "$ cd a",
        "$ ls",
        "dir e",
        "29116 f",
        "2557 g",
        "62596 h.lst",
        "$ cd e",
        "$ ls",
        "584 i",
        "$ cd ..",
        "$ cd ..",
        "$ cd d",
        "$ ls",
        "4060174 j",
        "8033020 d.log",
        "5626152 d.ext",
        "7214296 k"
    )

    @Test
    internal fun day06TestParseInputToTree() {
        //arrange
        val spaceUsage = SpaceOnDevice(fileTreeRawInput)

        //act
        val dirSizeSum = spaceUsage.getDirSizeSum(100000)

        //assert
        assertEquals(dirSizeSum, 95437)
    }
}