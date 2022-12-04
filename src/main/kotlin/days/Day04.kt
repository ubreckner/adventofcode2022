package days

private const val DAY = 4

fun main() {
    val input = loadInput(DAY)
    val testInput: List<String> = loadTestInput(DAY)

    with(input) {
        part1(this)
        part2(this)
    }
}

private fun part1(input: List<String>) {
    val res =
        input.map { it.split(",") }.map { topList -> topList.map { it.split("-") } }
            .filter { filterPredicatePart1(it) }.size
    println("Day $DAY, Part 1: $res")
}

private fun part2(input: List<String>) {
    val res =
        input.map { it.split(",") }.map { topList -> topList.map { it.split("-") } }
            .filter { filterPredicatePart2(it) }.size
    println("Day $DAY, Part 2: $res")
}

fun filterPredicatePart1(list: List<List<String>>): Boolean {
    var res = false
    //top contained in bottom
    res = res || list[0][0].toInt() >= list[1][0].toInt() && list[0][1].toInt() <= list[1][1].toInt()
    //bottom contained in top
    res = res || list[0][1].toInt() >= list[1][1].toInt() && list[0][0].toInt() <= list[1][0].toInt()
    return res
}

fun filterPredicatePart2(list: List<List<String>>): Boolean {
    var res = false
    //left from second
    res = res || list[0][1].toInt() < list[1][0].toInt()
    //right from second
    res = res || list[0][0].toInt() > list[1][1].toInt()
    return !res
}