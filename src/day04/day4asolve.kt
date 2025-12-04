package day04

import java.io.File

private val NEIGHBOR_DELTAS = listOf(
    -1 to -1, -1 to 0, -1 to 1,
    0 to -1,             0 to 1,
    1 to -1, 1 to 0, 1 to 1
)

fun day4parta() {
    val fileName = "/Users/kit/Documents/code/aoc2025/src/day04/day4input"
//    val fileName = "/Users/kit/Documents/code/aoc2025/src/exampledata"

    val grid = readGrid(fileName)
    val accessibleRolls = countAccessibleRolls(grid)

    println("Accessible rolls: $accessibleRolls")
}

private fun readGrid(path: String): List<String> =
    File(path)
        .readLines()
        .filter { it.isNotEmpty() }

private fun countAccessibleRolls(grid: List<String>): Int {
    var accessible = 0
    for (row in grid.indices) {
        val line = grid[row]
        for (col in line.indices) {
            if (line[col] != '@') continue
            if (hasFewerThanFourNeighbors(row, col, grid)) {
                accessible++
            }
        }
    }
    return accessible
}

private fun hasFewerThanFourNeighbors(row: Int, col: Int, grid: List<String>): Boolean {
    var count = 0
    for ((deltaRow, deltaCol) in NEIGHBOR_DELTAS) {
        val neighborRowIndex = row + deltaRow
        if (neighborRowIndex !in grid.indices) continue

        val neighborRow = grid[neighborRowIndex]
        val neighborColIndex = col + deltaCol
        if (neighborColIndex !in neighborRow.indices) continue

        if (neighborRow[neighborColIndex] == '@') {
            count++
            if (count >= 4) return false
        }
    }

    return true
}
