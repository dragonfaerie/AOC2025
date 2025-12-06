package day06

import java.io.File

fun day6partb() {
    val fileName = "/Users/kit/Documents/code/aoc2025/src/day06/day6input"
//    val fileName = "/Users/kit/Documents/code/aoc2025/src/exampledata"

    val rawRows = File(fileName).readLines()
    if (rawRows.isEmpty()) {
        println("Sum of all math problems: 0")
        return
    }

    val width = rawRows.maxOf { it.length }
    val rows = rawRows.map { it.padEnd(width, ' ') }
    val digitRowCount = rows.size - 1

    var total = 0L
    val currentNumbers = mutableListOf<Long>()
    var currentOperator: Char? = null

    fun flushProblem() {
        if (currentNumbers.isEmpty()) return
        val op = currentOperator ?: error("Missing operator for problem with numbers $currentNumbers")
        val orderedNumbers = currentNumbers.asReversed()
        val value = when (op) {
            '+' -> orderedNumbers.sum()
            '*' -> orderedNumbers.fold(1L) { acc, number -> acc * number }
            else -> error("Unknown operator '$op'")
        }
        total += value
        currentNumbers.clear()
        currentOperator = null
    }

    for (column in 0 until width) {
        val columnChars = rows.map { it[column] }
        if (columnChars.all { it == ' ' }) {
            flushProblem()
            continue
        }

        val digits = buildString {
            for (row in 0 until digitRowCount) {
                val ch = columnChars[row]
                if (ch.isDigit()) append(ch)
            }
        }

        val number = digits.takeIf { it.isNotEmpty() }?.toLong() ?: 0L
        currentNumbers.add(number)

        val opChar = columnChars.last()
        if (opChar == '+' || opChar == '*') {
            currentOperator = opChar
        }
    }

    flushProblem()

    println("Sum of all math problems: $total")
}
