package day03

import java.io.File

fun day3partb() {
    val fileName = "/Users/kit/Documents/code/aoc2025/src/day03/day4input"
//    val fileName = "/Users/kit/Documents/code/aoc2025/src/exampledata"

    var acc = 0L

    val banks = File(fileName)
        .readLines()
        .flatMap { it.split(",") }
        .map { it.trim() }
        .filter { it.isNotEmpty() }

    banks.forEach {  bank ->
        println(bank)
        val batteries = bank.map { it.toString().toInt() }
        println(batteries)

        val topDigits = highestOrderedSequence(batteries, 12)
            ?: error("Bank $bank must contain at least twelve digits")
        println("topDigits: $topDigits")

        val joltage = topDigits.joinToString("")

        println("joltage: $joltage")

        acc += joltage.toLong()
    }

    println("Total sum: ${acc}")
}

private fun highestOrderedSequence(digits: List<Int>, count: Int): List<Int>? {
    if (digits.size < count) return null

    var drops = digits.size - count
    val stack = ArrayList<Int>(digits.size)

    for (digit in digits) {
        while (stack.isNotEmpty() && drops > 0 && stack.last() < digit) {
            stack.removeAt(stack.lastIndex)
            drops--
        }
        stack.add(digit)
    }

    while (stack.size > count) {
        stack.removeAt(stack.lastIndex)
    }

    return stack
}
