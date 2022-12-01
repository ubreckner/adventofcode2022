package days

private const val DAY = 1

fun main() {
    val input = loadInput(DAY)
    val testInput: List<String> = loadTestInput(DAY)

    with(input) {
        part1(this)
        part2(this)
    }
}

private fun getElfCaloriesList(input: List<String>): MutableList<MutableList<Int>> {
    return input.fold(mutableListOf(mutableListOf(0))) { acc, str ->
        if (str.isEmpty()) {
            acc.add(mutableListOf())
        } else {
            acc.last().add(str.toInt())
        }
        acc
    }
}

private fun part1(input: List<String>) {
    println("Day $DAY, Part 1: ${getElfCaloriesList(input).maxOfOrNull { it.sum() }}")
}

private fun part2(input: List<String>) {
    val elfCalories = getElfCaloriesList(input)

    elfCalories.sortBy { it.sum() }

    println("Day $DAY, Part 2: ${elfCalories.takeLast(3).sumOf { it.sum() }}")
}