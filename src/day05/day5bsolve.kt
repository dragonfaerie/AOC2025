package day05

import java.io.File

fun day5partb() {
    val fileName = "/Users/kit/Documents/code/aoc2025/src/day05/day5input"
//    val fileName = "/Users/kit/Documents/code/aoc2025/src/exampledata"

    val inputs = File(fileName).readLines()

    val ranges = inputs
        .filter { "-" in it }
        .map { line ->
            val (start, end) = line.split("-").map { value -> value.toLong() }
            start to end
        }
        .sortedBy { it.first }

    if (ranges.isEmpty()) {
        println("Fresh Ingredients: 0")
        return
    }

    var currentStart = ranges.first().first
    var currentEnd = ranges.first().second
    var totalFresh = 0L

    // Merge overlapping ranges so we only count each ingredient once.
    ranges.drop(1).forEach { (start, end) ->
        if (start <= currentEnd) {
            currentEnd = maxOf(currentEnd, end)
        } else {
            totalFresh += currentEnd - currentStart + 1
            currentStart = start
            currentEnd = end
        }
    }

    totalFresh += currentEnd - currentStart + 1

    println("Fresh Ingredients: $totalFresh")
}
