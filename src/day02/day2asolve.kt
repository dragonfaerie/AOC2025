package day02

import java.io.File

fun day2parta() {
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

private fun isRepeater(value: Long): Boolean {
    val digits = value.toString()
    if (digits.length % 2 != 0) return false

    val half = digits.length / 2
    val first = digits.substring(0, half)
    val second = digits.substring(half)
    return first == second
}
