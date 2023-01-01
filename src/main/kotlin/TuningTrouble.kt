const val WINDOW_SIDE = 4

class TuningTrouble(_rawDataStreamsInput: List<String>) {
    private val rawDataStreamsInput = _rawDataStreamsInput

    private fun isValidMarker(str: String): Boolean {
        for (c in str) {
            if (str.count { it == c } >= 2) {
                return false
            }
        }
        return true
    }

    fun getFirstMarkerPosition(): List<Int> {
        val positons = mutableListOf<Int>()
        for (i in rawDataStreamsInput) {
            for (p in 0 until i.length - WINDOW_SIDE + 1) {
                val candidate = i.slice(p until p + WINDOW_SIDE)
                if (isValidMarker(candidate)) {
                    positons.add(p + WINDOW_SIDE)
                    break
                }
            }
        }
        return positons
    }
}
