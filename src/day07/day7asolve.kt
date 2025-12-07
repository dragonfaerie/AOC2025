package day07

import java.io.File

fun day7parta() {
    val fileName = "/Users/kit/Documents/code/aoc2025/src/day07/day7input"
//    val fileName = "/Users/kit/Documents/code/aoc2025/src/exampledata"

    val rows = File(fileName).readLines().filter { it.isNotBlank() }
    if (rows.isEmpty()) {
        println("Number of beams: 0")
        return
    }

    val startRowIndex = rows.indexOfFirst { it.contains('S') }
    if (startRowIndex == -1) {
        println("No starting point found")
        return
    }

    val startColumn = rows[startRowIndex].indexOf('S')
    var activeColumns = mutableSetOf(startColumn)
    var splitCount = 0L

    for (rowIndex in startRowIndex + 1 until rows.size) {
        if (activeColumns.isEmpty()) break
        val row = rows[rowIndex]
        val nextColumns = mutableSetOf<Int>()

        activeColumns.forEach { column ->
            val ch = row.getOrNull(column)
            if (ch == '^') {
                splitCount++
                val left = column - 1
                val right = column + 1
                if (left >= 0) nextColumns.add(left)
                if (right < row.length) nextColumns.add(right)
            } else if (ch != null) {
                nextColumns.add(column)
            }
        }

        activeColumns = nextColumns
    }

    println("Number of beams: $splitCount")
}
