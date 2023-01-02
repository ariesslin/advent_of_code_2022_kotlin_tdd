import java.util.*

class TreeNode(var name: String, var type: String, var size: Int) {
    var parent: TreeNode? = null
    var children: MutableList<TreeNode> = mutableListOf()

    fun addChild(node: TreeNode) {
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
        val rootNode = TreeNode("root", "dir", 0)
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
                    val childNode = TreeNode(childNodeName, "dir", 0)
                    currentNode.addChild(childNode)
                } else {
                    val childNodeName = s.substringAfter(" ")
                    val childNodeSize = s.substringBefore(" ").toInt()
                    val childNode = TreeNode(childNodeName, "file", childNodeSize)
                    currentNode.addChild(childNode)
                }
            }
        }

        return rootNode
    }

    fun getDirSizeSum(sizeLimit: Int): Int {
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

                map[child]?.let {
                    val value = map[top]!! + it
                    map[top] = value
                }
            }
        }

        // Update directory size
        for (node in map) {
            if (node.key.type == "dir") {
                for (c in node.key.children) {
                    if (c.type == "dir") {
                        val value = map[node.key]!! + map[c]!!
                        map[node.key] = value
                    }
                }
            }
        }

        var sum = 0
        for (node in map) {
            if (node.key.type == "dir" && node.value <= sizeLimit) {
                sum += node.value
            }
        }

        return sum
    }
}
