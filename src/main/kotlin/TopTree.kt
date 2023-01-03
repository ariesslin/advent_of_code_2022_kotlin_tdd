enum class Direction {
    LEFT,
    RIGHT,
    UP,
    DOWN
}

class Tree(var x: Int, var y: Int, var height: Int) {
    var isVisible: Boolean = false
    var sceneScore: Int = 1
    fun updateVisibility(isVisible: Boolean) {
        this.isVisible = isVisible
    }

    fun updateSceneScore(sceneScore: Int) {
        this.sceneScore = sceneScore
    }

    override fun toString(): String {
        var s = ""
        if (x == 0) {
            s += "\n"
        }
        s += "($x, $y) $height is visible: $isVisible, score: $sceneScore"
        return s
    }
}

class TopTree(private val forest: List<String>) {

    private fun parseForestToTrees(): List<List<Tree>> {
        var x: Int
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
        return treeList
    }

    fun getVisibleTreeNum(): Int {
        val treeList = parseForestToTrees()
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

        fun updateLeftRightView(treeList: List<List<Tree>>, direction: Direction) {
            for (r in treeList.subList(1, rowLength - 1)) {
                val currentR = if (direction == Direction.LEFT) {
                    r
                } else {
                    r.reversed()
                }
                var minLeft = currentR[0].height
                for (c in currentR.subList(1, columnLength - 1)) {
                    if (minLeft < c.height) {
                        c.updateVisibility(true)
                        minLeft = c.height
                    }
                }
            }
        }

        updateLeftRightView(treeList, Direction.LEFT)
        updateLeftRightView(treeList, Direction.RIGHT)

        fun updateUpDownView(treeList: List<List<Tree>>, direction: Direction) {
            val currentT = if (direction == Direction.UP) {
                treeList
            } else {
                treeList.reversed()
            }
            val minTops = currentT[0].subList(1, columnLength - 1).map { it.height }.toMutableList()
            for (r in currentT.subList(1, rowLength - 1)) {
                for ((index, c) in r.subList(1, columnLength - 1).withIndex()) {
                    if (minTops[index] < c.height) {
                        c.updateVisibility(true)
                        minTops[index] = c.height
                    }
                }
            }
        }

        updateUpDownView(treeList, Direction.UP)
        updateUpDownView(treeList, Direction.DOWN)

        var sum = 0

        for (r in treeList) {
            for (c in r) {
                if (c.isVisible)
                    sum += 1
            }
        }
        return sum
    }

    fun getHighestSceneScore(): Int {
        val treeList = parseForestToTrees()
        val rowLength = treeList.size
        val columnLength = treeList[0].size

        fun updateLeftRightView(treeList: List<List<Tree>>, direction: Direction) {
            for (r in treeList.subList(1, rowLength - 1)) {
                val currentR = if (direction == Direction.LEFT) {
                    r
                } else {
                    r.reversed()
                }
                var leftHeight = currentR[0].height
                var count: Int
                for ((index, c) in currentR.subList(1, columnLength - 1).withIndex()) {
                    if (leftHeight >= c.height) {
                        count = 1
                        c.updateSceneScore(count * c.sceneScore)
                    } else {
                        count = 0
                        for (i in index downTo 0) {
                            if (currentR[i].height < c.height) {
                                count++
                            } else {
                                count++
                                break
                            }
                        }
                        c.updateSceneScore(count * c.sceneScore)
                    }
                    leftHeight = c.height
                }
            }
        }

        updateLeftRightView(treeList, Direction.LEFT)
        updateLeftRightView(treeList, Direction.RIGHT)

        fun updateUpDownView(treeList: List<List<Tree>>, direction: Direction) {
            val currentT = if (direction == Direction.UP) {
                treeList
            } else {
                treeList.reversed()
            }

            val topHeights = currentT[0].subList(1, columnLength - 1).map { it.height }.toMutableList()
            val counts = currentT[0].subList(1, columnLength - 1).map { 0 }.toMutableList()
            for ((rIndex, r) in currentT.subList(1, rowLength - 1).withIndex()) {
                for ((index, c) in r.subList(1, columnLength - 1).withIndex()) {
                    if (topHeights[index] >= c.height) {
                        counts[index] = 1
                        c.updateSceneScore(counts[index] * c.sceneScore)
                    } else {
                        counts[index] = 0
                        for (i in rIndex downTo 0) {
                            if (currentT[i].subList(
                                    1,
                                    columnLength - 1
                                )[index].height < c.height
                            ) {
                                counts[index]++
                            } else {
                                counts[index]++
                                break
                            }
                        }
                        c.updateSceneScore(counts[index] * c.sceneScore)
                    }
                    topHeights[index] = c.height
                }
            }
        }

        updateUpDownView(treeList, Direction.UP)
        updateUpDownView(treeList, Direction.DOWN)
        
        var max = 0

        for (r in treeList) {
            for (c in r) {
                if (c.sceneScore > max)
                    max = c.sceneScore
            }
        }
        return max
    }
}