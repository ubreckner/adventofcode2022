package days

private const val DAY = 2

fun main() {
    val input = loadInput(DAY)
    val testInput: List<String> = loadTestInput(DAY)

    with(testInput) {
        part1(this)
        part2(this)
    }
}

private fun part1(input: List<String>) {
    println("Day $DAY, Part 1: ")
}

private fun part2(input: List<String>) {
    println("Day $DAY, Part 2: ")
}