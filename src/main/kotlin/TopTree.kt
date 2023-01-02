class Tree(var x: Int, var y: Int, var height: Int, var isVisible: Boolean = false) {
    fun updateVisibility(isVisible: Boolean) {
        this.isVisible = isVisible
    }

    override fun toString(): String {
        var s = ""
        if (x == 0) {
            s += "\n"
        }
        s += "($x, $y) $height is visible: $isVisible"
        return s
    }
}

class TopTree(_forest: List<String>) {
    private val forest = _forest

    fun parseForestToTrees(): List<List<Tree>> {
        var x = -1
        var y = -1
        val treeList = mutableListOf<List<Tree>>()
        for (row in forest) {
            x = -1
            y += 1
            val columnList = mutableListOf<Tree>()
            for (column in row) {
                x += 1
                columnList.add(Tree(x, y, column.toString().toInt()))
            }
            treeList.add(columnList)
        }

        val rowLength = treeList.size
        val columnLength = treeList[0].size

        // Outside View
        for (r in treeList) {
            for (c in r) {
                if (c.x == 0 || c.y == 0 || c.x == columnLength - 1 || c.y == rowLength - 1) {
                    c.updateVisibility(true)
                }
            }
        }

        // Left View
        for (r in treeList.subList(1, rowLength - 1)) {
            var minLeft = r[0].height
            for (c in r.subList(1, columnLength - 1)) {
                if (minLeft < c.height) {
                    c.updateVisibility(true)
                    minLeft = c.height
                }
            }
        }

        // Right view
        for (r in treeList.subList(1, rowLength - 1)) {
            var minRight = r.reversed()[0].height
            for (c in r.reversed().subList(1, r.size - 1)) {
                if (minRight < c.height) {
                    c.updateVisibility(true)
                    minRight = c.height
                }
            }
        }

        // Upside view
        var minTops = treeList[0].subList(1, columnLength - 1).map { it.height }.toMutableList()
        for (r in treeList.subList(1, rowLength - 1)) {
            for ((index, c) in r.subList(1, columnLength - 1).withIndex()) {
                if (minTops[index] < c.height) {
                    c.updateVisibility(true)
                    minTops[index] = c.height
                }
            }
        }

        // Downside view
        var minBottoms = treeList[rowLength - 1].subList(1, columnLength - 1).map { it.height }.toMutableList()
        for (r in treeList.reversed().subList(1, rowLength - 1)) {
            for ((index, c) in r.subList(1, columnLength - 1).withIndex()) {
                if (minBottoms[index] < c.height) {
                    c.updateVisibility(true)
                    minBottoms[index] = c.height
                }
            }
        }

        return treeList
    }


    fun getVisibleTreeNum(): Int {
        val trees = parseForestToTrees()
        var sum = 0

        for (r in trees) {
            for (c in r) {
                if (c.isVisible)
                    sum += 1
            }
        }
        return sum
    }

}