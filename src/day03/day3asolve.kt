package day03

import java.io.File

fun day3parta() {
    val fileName = "/Users/kit/Documents/code/aoc2025/src/day03/day3input"
//    val fileName = "/Users/kit/Documents/code/aoc2025/src/exampledata"

    var acc = 0

    val banks = File(fileName)
        .readLines()
        .flatMap { it.split(",") }
        .map { it.trim() }
        .filter { it.isNotEmpty() }

    banks.forEach {  bank ->
        val batteries = bank.map { it.toString().toInt() }

        val (value1, value2) = highestOrderedPair(batteries)
            ?: error("Bank $bank must contain at least two digits")

        val joltage = value1.toString() + value2.toString()

        println("joltage: $joltage")

        acc += joltage.toInt()
    }

    println("Total sum: ${acc}")
}

private fun highestOrderedPair(digits: List<Int>): Pair<Int, Int>? {
    if (digits.size < 2) return null

    val suffixMax = IntArray(digits.size)
    suffixMax[digits.lastIndex] = digits.last()
    for (index in digits.size - 2 downTo 0) {
        suffixMax[index] = maxOf(digits[index], suffixMax[index + 1])
    }

    var bestValue = -1
    var bestFirst = -1
    var bestSecond = -1

    for (index in 0 until digits.size - 1) {
        val first = digits[index]
        val second = suffixMax[index + 1]
        val candidateValue = first * 10 + second
        if (candidateValue > bestValue) {
            bestValue = candidateValue
            bestFirst = first
            bestSecond = second
        }
    }

    return if (bestFirst >= 0) Pair(bestFirst, bestSecond) else null
}
