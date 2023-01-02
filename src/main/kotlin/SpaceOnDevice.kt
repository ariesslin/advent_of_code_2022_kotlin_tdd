import java.util.*

const val DISKSPACE = 70000000
const val SPACE_NEEDED_FOR_UPDATE = 30000000

class TreeNode(var name: String, var type: String, var size: Int, var path: String = "") {
    var parent: TreeNode? = null
    var children: MutableList<TreeNode> = mutableListOf()

    fun addChild(node: TreeNode) {
        node.path = node.path.replace("//", "/")

        children.add(node)
        node.parent = this
    }

    override fun toString(): String {
        var s = ""
        var p = parent
        var prefix = ""
        while (p != null) {
            prefix += "\t"
            p = p.parent
        }
        s += "\n" + prefix + "$name [${type}] $size"
        if (children.isNotEmpty()) {
            s += "\n" + prefix + "\t{" + children.map { it.toString() } + "} "
        }
        return s
    }
}

class SpaceOnDevice(_fileTreeRawInput: List<String>) {
    private val fileTreeRawInput = _fileTreeRawInput

    private fun parseInputToTree(): TreeNode {
        val rootNode = TreeNode("root", "dir", 0, "/")
        var currentNode = rootNode
        for (s in fileTreeRawInput) {
            if (s.startsWith("\$ cd")) {
                when (s) {
                    "\$ cd /" -> {
                        currentNode = rootNode
                    }
                    "\$ cd .." -> {
                        currentNode = currentNode.parent!!
                    }
                    else -> {
                        val childNodeName = s.substringAfterLast(" ")
                        for (n in currentNode.children) {
                            if (n.name == childNodeName) {
                                currentNode = n
                            }
                        }
                    }
                }
            } else if (s.startsWith("\$ ls")) {
                continue
            } else {
                if (s.contains("dir")) {
                    val childNodeName = s.substringAfter(" ")
                    val childNode = TreeNode(childNodeName, "dir", 0, currentNode.path + "/" + childNodeName)
                    currentNode.addChild(childNode)
                } else {
                    val childNodeName = s.substringAfter(" ")
                    val childNodeSize = s.substringBefore(" ").toInt()
                    val childNode =
                        TreeNode(childNodeName, "file", childNodeSize, currentNode.path + "/" + childNodeName)
                    currentNode.addChild(childNode)
                }
            }
        }

        return rootNode
    }

    private fun calcDirSize(): HashMap<TreeNode, Int> {
        val rootNode = parseInputToTree()

        // Iterative DFS
        val stack = LinkedList<TreeNode>()
        stack.push(rootNode)

        val map = hashMapOf(rootNode to rootNode.size)

        while (stack.isNotEmpty()) {
            val top = stack.pop()

            for (child in top.children) {
                if (map[child] == null) {
                    map[child] = child.size
                    stack.push(child)
                }
            }
        }

        // Update directory size
        val nodeList: List<TreeNode> = map.keys.toList().sortedByDescending { it.path }

        for (node in nodeList) {
            if (node.type == "dir") {
                for (c in node.children) {
                    val value = map[node]!! + map[c]!!
                    map[node] = value
                }
            }
        }

        return map
    }

    fun getDirSizeSum(sizeLimit: Int): Int {
        val map = calcDirSize()

        var sum = 0
        for (node in map) {
            if (node.key.type == "dir" && node.value <= sizeLimit) {
                sum += node.value
            }
        }

        return sum
    }

    fun getDirToDelete(): Pair<String, Int> {
        val map = calcDirSize()

        var spaceNeeded = 0
        for (node in map) {
            if (node.key.name == "root") {
                spaceNeeded = SPACE_NEEDED_FOR_UPDATE - (DISKSPACE - node.value)
            }
        }
        
        var minNodeName = ""
        var minSpace = DISKSPACE
        for (node in map) {
            if (node.key.type == "dir" && node.value >= spaceNeeded) {
                if (node.value < minSpace) {
                    minNodeName = node.key.name
                    minSpace = node.value
                }
            }
        }

        return Pair(minNodeName, minSpace)
    }
}
