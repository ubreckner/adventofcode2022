package days


fun main() {
    val day = 1
    val input = loadInput(day)
    val testInput: List<String> = loadTestInput(day)

    with(input) {
        part1(this)
        part2(this)
    }
}

fun getElfCaloriesList(input: List<String>): MutableList<MutableList<Int>> {
    return input.fold(mutableListOf(mutableListOf(0))) { acc, str ->
        if (str.isEmpty()) {
            acc.add(mutableListOf())
        } else {
            acc.last().add(str.toInt())
        }
        acc
    }
}

fun part1(input: List<String>) {
    println("Day 1, Part 1: ${getElfCaloriesList(input).maxOfOrNull { it.sum() }}")
}

fun part2(input: List<String>) {
    val elfCalories = getElfCaloriesList(input)

    elfCalories.sortBy { it.sum() }

    println("Day 1, Part 2: ${elfCalories.takeLast(3).sumOf { it.sum() }}")
}