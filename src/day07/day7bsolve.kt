package day07

import java.io.File

fun day7partb() {
    val fileName = "/Users/kit/Documents/code/aoc2025/src/day07/day7input"
//    val fileName = "/Users/kit/Documents/code/aoc2025/src/exampledata"

    val rows = File(fileName).readLines().filter { it.isNotBlank() }
    if (rows.isEmpty()) {
        println("Number of paths: 0")
        return
    }

    val startRowIndex = rows.indexOfFirst { it.contains('S') }
    if (startRowIndex == -1) {
        println("No starting point found")
        return
    }

    val startColumn = rows[startRowIndex].indexOf('S')
    var beamCounts = mutableMapOf(startColumn to 1L)

    for (rowIndex in startRowIndex + 1 until rows.size) {
        if (beamCounts.isEmpty()) break
        val row = rows[rowIndex]
        val nextCounts = mutableMapOf<Int, Long>()

        beamCounts.forEach { (column, beams) ->
            val ch = row.getOrNull(column)
            if (ch == '^') {
                val left = column - 1
                val right = column + 1
                if (left >= 0) {
                    nextCounts[left] = nextCounts.getOrDefault(left, 0L) + beams
                }
                if (right < row.length) {
                    nextCounts[right] = nextCounts.getOrDefault(right, 0L) + beams
                }
            } else if (ch != null) {
                nextCounts[column] = nextCounts.getOrDefault(column, 0L) + beams
            }
        }

        beamCounts = nextCounts
    }

    val pathCount = beamCounts.values.sum()
    println("Number of paths: $pathCount")
}
