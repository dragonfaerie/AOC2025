package day02

import java.io.File

fun day2partb() {
    val fileName = "/Users/kit/Documents/code/aoc2025/src/day02/day2input"
//    val fileName = "/Users/kit/Documents/code/aoc2025/src/exampledata"

    val idRanges = File(fileName)
        .readLines()
        .flatMap { it.split(",") }
        .map { it.trim() }
        .filter { it.isNotEmpty() }

    val repeaters = mutableListOf<Long>()

    idRanges.forEach { idRange ->
        val start = idRange.substringBefore('-').trim().toLong()
        val end = idRange.substringAfter('-').trim().toLong()

        println("$start..$end")

        for (candidate in start..end) {
            if (isRepeater(candidate)) {
                repeaters += candidate
                println("$candidate is a repeater")
            }
        }
    }

    val sum = repeaters.sum()

    println("Total repeaters: ${repeaters.size}")
    println("Total sum: ${sum}")
}

// Accepts values made from any substring repeated >= 2 times.
private fun isRepeater(value: Long): Boolean {
    val digits = value.toString()
    val length = digits.length

    for (patternLength in 1..length / 2) {
        if (length % patternLength != 0) continue

        val pattern = digits.substring(0, patternLength)
        val repeatCount = length / patternLength
        if (repeatCount > 1 && pattern.repeat(repeatCount) == digits) {
            return true
        }
    }

    return false
}
