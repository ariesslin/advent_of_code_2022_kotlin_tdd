package utils

import java.io.File
import java.nio.file.Paths

fun readFileAsLinesToListString(fileName: String?): List<String> {
    if (fileName.isNullOrEmpty()) {
        return emptyList()
    }

    val projectDirAbsolutePath = Paths.get("").toAbsolutePath().toString()
    val resourcesPath = Paths.get(projectDirAbsolutePath, "/src/main/resources")

    return File("$resourcesPath/$fileName").useLines { it.toList() }
}

fun readFileAsLinesToListInt(fileName: String?): List<Int?> {
    val linesOfFileInString = readFileAsLinesToListString(fileName)
    val linesOfFileInInt = linesOfFileInString.map {
        if (it.isEmpty()) {
            null
        } else {
            it.toInt()
        }
    }
    return linesOfFileInInt
}
