package day04

import java.io.File

private val NEIGHBOR_DELTAS = listOf(
    -1 to -1, -1 to 0, -1 to 1,
    0 to -1,             0 to 1,
    1 to -1, 1 to 0, 1 to 1
)

fun day4partb() {
    val fileName = "src/day04/day4input"
//    val fileName = "src/exampledata"

    val grid = readMutableGrid(fileName)
    val removed = simulateRollRemoval(grid)

    println("Total removed rolls: $removed")
}

private fun readMutableGrid(path: String): MutableList<CharArray> =
    File(path)
        .readLines()
        .filter { it.isNotEmpty() }
        .map { it.toCharArray() }
        .toMutableList()

private fun simulateRollRemoval(grid: MutableList<CharArray>): Int {
    var removed = 0
    while (true) {
        val pending = findAccessibleRolls(grid)
        if (pending.isEmpty()) break
        pending.forEach { (row, col) -> grid[row][col] = '.' }
        removed += pending.size
    }
    return removed
}

private fun findAccessibleRolls(grid: List<CharArray>): List<Pair<Int, Int>> {
    val matches = ArrayList<Pair<Int, Int>>()
    for (row in grid.indices) {
        val line = grid[row]
        for (col in line.indices) {
            if (line[col] != '@') continue
            if (hasFewerThanFourNeighbors(row, col, grid)) {
                matches += row to col
            }
        }
    }
    return matches
}

private fun hasFewerThanFourNeighbors(row: Int, col: Int, grid: List<CharArray>): Boolean {
    var neighbors = 0
    for ((deltaRow, deltaCol) in NEIGHBOR_DELTAS) {
        val neighborRowIndex = row + deltaRow
        if (neighborRowIndex !in grid.indices) continue

        val neighborRow = grid[neighborRowIndex]
        val neighborColIndex = col + deltaCol
        if (neighborColIndex !in neighborRow.indices) continue

        if (neighborRow[neighborColIndex] == '@') {
            neighbors++
            if (neighbors >= 4) return false
        }
    }

    return true
}
