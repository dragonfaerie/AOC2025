package day05

import java.io.File

fun day5parta() {
    val fileName = "/Users/kit/Documents/code/aoc2025/src/day05/day5input"
//    val fileName = "/Users/kit/Documents/code/aoc2025/src/exampledata"

    val inputs = File(fileName).readLines()

    val ranges = mutableListOf<String>()
    val ingredients = mutableListOf<String>()
    var counter = 0

    inputs.forEach { inputLine ->
        if (inputLine.indexOf("-") > 0 ) {
            ranges.add(inputLine)
        } else {
            ingredients.add(inputLine)
        }
    }

    ingredients.forEach { ingredient ->
        if (ingredient.isNotEmpty()) {
            val value = ingredient.toLong()
            for (range in ranges) {
                val (start, end) = range.split("-").map { it.toLong() }
                if (value in start..end) {
                    counter++
                    break
                }
            }
        }
    }

    println("Fresh Ingredients: $counter")
}
