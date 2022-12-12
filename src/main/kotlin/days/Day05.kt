package days

private const val DAY = 5

fun main() {
    val input = loadInput(DAY)
    val testInput: List<String> = loadTestInput(DAY)

    with(input) {
        part1(this)
        part2(this)
    }
}

private fun getInputs(input: List<String>): MutableList<MutableList<String>> {
    return input.fold(mutableListOf(mutableListOf())) { acc, str ->
        if (str.isEmpty()) {
            acc.add(mutableListOf())
        } else {
            acc.last().add(str)
        }
        acc
    }
}

private fun part1(input: List<String>) {

    val inputs = getInputs(input)
    var stacks = extractStartingStacks(inputs[0])

    //extract moveset vie regex capturegroups
    val moves = inputs[1].map { it.replace("(move )(\\d+)( from )(\\d+)( to )(\\d+)".toRegex(), "\$2 \$4 \$6") }
    //make the moves
    for (line in moves) {
        val (amount, from, to) = line.split(" ").map { it.toInt() }
        for (x in 1..amount) {
            val temp = stacks[from - 1].removeLast()
            stacks[to - 1].addLast(temp)
        }
    }
    val res = stacks.joinToString("") { it.last() }
    println("Day $DAY, Part 1: $res")
}

private fun part2(input: List<String>) {

    val inputs = getInputs(input)
    var stacks = extractStartingStacks(inputs[0])

    //extract moveset vie regex capturegroups
    val moves = inputs[1].map { it.replace("(move )(\\d+)( from )(\\d+)( to )(\\d+)".toRegex(), "\$2 \$4 \$6") }
    //make the moves
    for (line in moves) {
        val (amount, from, to) = line.split(" ").map { it.toInt() }
        val temp = stacks[from - 1].takeLast(amount)
        repeat(amount) { stacks[from - 1].removeLast() }
        stacks[to - 1].addAll(temp)
    }
    val res = stacks.joinToString("") { it.last() }
    println("Day $DAY, Part 2: $res")
}

fun extractStartingStacks(strings: MutableList<String>): List<ArrayDeque<String>> {
    val res = strings.map {
        //replace empty stack positions, strip of remaining whitespaces, delete brackets
        it.replace("    ", "0").replace("\\s".toRegex(), "").replace("[\\[\\]]".toRegex(), "")
    }
        //delete last row - col numbers
        .dropLast(1)
        //create array of single characters
        .map { it.chunked(1) }
    //create final stacks without "empty" stack positions
    return transpose(res).map { list -> ArrayDeque(list.filter { it != "0" }.reversed()) }
}