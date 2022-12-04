package days

private const val DAY = 3

fun main() {
    val input = loadInput(DAY)
    val testInput: List<String> = loadTestInput(DAY)

    with(input) {
        part1(this)
        part2(this)
    }
}

private fun part1(input: List<String>) {
    val rucksackCompartments = input.map { Pair(it.substring(0 until it.length / 2), it.substring(it.length / 2)) }
    val res =
        rucksackCompartments.map { comp -> comp.first.filter { it in comp.second }.toCharArray().distinct()[0] }.sumOf {
            charToValue(it)
        }

    println("Day $DAY, Part 1: $res")
}

private fun part2(input: List<String>) {
    val res =
        input.chunked(3)
            .map { sublist -> sublist[0].filter { it in sublist[1] && it in sublist[2] }.toCharArray().distinct()[0] }
            .sumOf { charToValue(it) }
    println("Day $DAY, Part 2: $res")
}

fun charToValue(character: Char): Int {
    return if (character.isUpperCase()) {
        character.code - 38
    } else {
        character.code - 96
    }
}
