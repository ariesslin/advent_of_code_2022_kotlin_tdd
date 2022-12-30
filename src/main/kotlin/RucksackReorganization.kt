class RucksackReorganization(_rucksackList: List<String>) {
    private val rucksackList = _rucksackList

    private fun getItemType(rucksack: String): Char {
        val length = rucksack.length
        val first = rucksack.slice(0 until length / 2).toList()
        val second = rucksack.slice(length / 2 until length).toList()

        val common = first.intersect(second.toSet())
        return common.first()
    }

    private fun getPriority(type: Char): Int {
        for (c in 'a'..'z') {
            if (c == type)
                return type - 'a' + 1;
        }

        for (c in 'A'..'Z') {
            if (c == type)
                return type - 'A' + 27;
        }

        return -1
    }

    fun getRucksackPriorities(): List<Int> {
        val rucksackPriorities = mutableListOf<Int>()
        var itemType: Char
        var priority: Int

        for (r in rucksackList) {
            itemType = getItemType(r)
            priority = getPriority(itemType)
            rucksackPriorities.add(priority)
        }
        return rucksackPriorities
    }

    fun getRucksackPrioritiesSum(rucksackPriorities: List<Int>): Int {
        var sum = 0
        for (p in rucksackPriorities) {
            sum += p
        }
        return sum
    }
}