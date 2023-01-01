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
    internal fun day06TestGetFirstMarkerPositionIfWindowSizeIs4() {
        //arrange
        val dataStreams = TuningTrouble(rawDataStreamsInput)

        //act
        val markerPos = dataStreams.getFirstMarkerPosition(windowSize = 4)

        //assert
        assertEquals(markerPos[0], 7)
        assertEquals(markerPos[1], 5)
        assertEquals(markerPos[2], 6)
        assertEquals(markerPos[3], 10)
        assertEquals(markerPos[4], 11)
    }

    @Test
    internal fun day06TestGetFirstMarkerPositionIfWindowSizeIs14() {
        //arrange
        val dataStreams = TuningTrouble(rawDataStreamsInput)

        //act
        val markerPos = dataStreams.getFirstMarkerPosition(windowSize = 14)

        //assert
        assertEquals(markerPos[0], 19)
        assertEquals(markerPos[1], 23)
        assertEquals(markerPos[2], 23)
        assertEquals(markerPos[3], 29)
        assertEquals(markerPos[4], 26)
    }
}