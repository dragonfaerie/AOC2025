package day06

import java.io.File

fun day6parta() {
    val fileName = "/Users/kit/Documents/code/aoc2025/src/day06/day6input"
//    val fileName = "/Users/kit/Documents/code/aoc2025/src/exampledata"

    val rows = File(fileName).readLines().filter { it.isNotBlank() }

    val dataRows = rows.take(4).map { it.trim().split(Regex("\\s+")) }
    val operatorRow = rows[4].trim().split(Regex("\\s+"))

    val columnCount = dataRows.first().size
    var sum = 0L

    for (column in 0 until columnCount) {
        val op = operatorRow.getOrNull(column)
            ?: error("Missing operator for column $column")
        val values = dataRows.map { it[column].toLong() }

        val columnResult = when (op) {
            "+" -> values.sum()
            "*" -> values.reduce { acc, value -> acc * value }
            else -> error("Unknown operator '$op' in column $column")
        }

        sum += columnResult
    }

    println("Sum of all math problems: $sum")
}
