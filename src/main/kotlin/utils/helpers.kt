package utils

import java.io.File
import java.nio.file.Paths

fun readFileAsLinesUsingUseLines(fileName: String?): List<Int?> {
    if (fileName.isNullOrEmpty()) {
        return emptyList()
    }

    val projectDirAbsolutePath = Paths.get("").toAbsolutePath().toString()
    val resourcesPath = Paths.get(projectDirAbsolutePath, "/src/main/resources")

    val linesOfFileInString = File("$resourcesPath/$fileName").useLines { it.toList() }
    val linesOfFileInInt = linesOfFileInString.map {
        if (it.isEmpty()) {
            null
        } else {
            it.toInt()
        }
    }
    return linesOfFileInInt
}
