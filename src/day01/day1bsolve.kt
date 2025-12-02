package day01

import java.io.File

fun day1partb() {
    val fileName = "/Users/kit/Documents/code/aoc2025/src/day01/day1input"
//    val fileName = "/Users/kit/Documents/code/aoc2025/src/exampledata"

    val dialSize = 100L
    var absolutePosition = 50L
    var zeroLandingCount = 0L
    var zeroPassCount = 0L

    File(fileName).forEachLine { movement ->
        if (movement.isBlank()) return@forEachLine

        val direction = movement.first()
        val amount = movement.substring(1).toLong()
        val positionBefore = absolutePosition

        absolutePosition = if (direction == 'R') {
            absolutePosition + amount
        } else {
            absolutePosition - amount
        }

        zeroPassCount += zeroPassesBetween(positionBefore, absolutePosition, dialSize)

        if (Math.floorMod(absolutePosition, dialSize) == 0L) {
            zeroLandingCount += 1
        }
    }

    println(zeroLandingCount + zeroPassCount)
}

private fun zeroPassesBetween(start: Long, end: Long, dialSize: Long): Long {
    if (start == end) return 0
    val lower = minOf(start, end)
    val upper = maxOf(start, end)
    val multiplesBelowUpper = Math.floorDiv(upper - 1, dialSize)
    val multiplesAtOrBelowLower = Math.floorDiv(lower, dialSize)
    return multiplesBelowUpper - multiplesAtOrBelowLower
}
