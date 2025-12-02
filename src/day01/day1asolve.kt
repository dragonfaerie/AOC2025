package day01

import java.io.File

fun day1parta() {
//    val fileName = "/Users/kit/Documents/code/aoc2025/src/day01/day1input"
    val fileName = "/Users/kit/Documents/code/aoc2025/src/exampledata"

    val dialMovements = mutableListOf<String>()
    File(fileName).forEachLine { line ->
        dialMovements.add(line)
    }

    var currentPosition = 50
    var counter = 0

    dialMovements.forEachIndexed { index, movement ->
        val direction = movement.take(1)
        val amount = movement.substring(1).toInt()

        currentPosition = if ( direction == "L") {
            (currentPosition - amount + 100) % 100
        } else {
            (currentPosition + amount) % 100
        }

        if (currentPosition == 0) {
            counter++
        }
    }

    println(counter)
}