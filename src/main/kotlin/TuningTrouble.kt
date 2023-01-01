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

    fun getFirstMarkerPosition(windowSize: Int): List<Int> {
        val positons = mutableListOf<Int>()
        for (i in rawDataStreamsInput) {
            for (p in 0 until i.length - windowSize + 1) {
                val candidate = i.slice(p until p + windowSize)
                if (isValidMarker(candidate)) {
                    positons.add(p + windowSize)
                    break
                }
            }
        }
        return positons
    }
}
