package days

private const val DAY = 2

fun main() {
    val input = loadInput(DAY)
    val testInput: List<String> = loadTestInput(DAY)

    with(input) {
        part1(this)
        part2(this)
    }
}

private fun part1(input: List<String>) {
    println("Day $DAY, Part 1: ${input.map { RpsRound(it) }.sumOf { it.getPointsPart1() }}")
}

private fun part2(input: List<String>) {
    println("Day $DAY, Part 2: ${input.map { RpsRound(it) }.sumOf { it.getPointsPart2() }}")
}

class RpsRound(line: String) {
    private val first: Char
    private val second: Char

    init {
        //all the way down to start from ascii 1
        first = line.split(" ")[0].first() - 64
        second = line.split(" ")[1].first() - 87
    }

    /**
     * Getting points while interpreting first argument as opponent and second argument as player
     *
     * A,X = rock
     *
     * B,Y = paper
     *
     * C,Z = scissors
     */
    fun getPointsPart1(): Int {
        //points for shape
        var res = second.code
        //points for outcome
        res += when {
            //overflow rules should match first
            first.code == 1 && second.code == 3 -> 0
            first.code == 3 && second.code == 1 -> 6
            //"normal" rules afterwards
            first > second -> 0
            first < second -> 6
            else -> 3
        }
        return res
    }

    /**
     * Getting points while interpreting first argument as opponent and second argument as desired outcome
     *
     * X = lose
     *
     * Y = draw
     *
     * Z = win
     */
    fun getPointsPart2(): Int {
        //points for shape
        var res = when (second.code) {
            1 -> first.code - 1
            2 -> first.code
            else -> first.code + 1
        }
        //handle overflow conditions
        res = when (res) {
            4 -> 1
            0 -> 3
            else -> res
        }
        //adding value of outcome
        res += (second.code - 1) * 3

        return res
    }

}