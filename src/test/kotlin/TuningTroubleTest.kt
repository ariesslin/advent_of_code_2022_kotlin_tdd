import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TuningTroubleTest {
    private val rawDataStreamsInput: List<String> = listOf(
        "mjqjpqmgbljsphdztnvjfqwrcgsmlb",
        "bvwbjplbgvbhsrlpgdmjqwftvncz",
        "nppdvjthqldpwncqszvftbrmjlhg",
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg",
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"
    )

    @Test
    internal fun day06TestGetFirstMarkerPosition() {
        //arrange
        val dataStreams = TuningTrouble(rawDataStreamsInput)

        //act
        val markerPos = dataStreams.getFirstMarkerPosition()

        //assert
        assertEquals(markerPos[0], 7)
        assertEquals(markerPos[1], 5)
        assertEquals(markerPos[2], 6)
        assertEquals(markerPos[3], 10)
        assertEquals(markerPos[4], 11)
    }
}