package days

private const val DAY = 6

fun main() {
    val input = loadInput(DAY)
    val testInput: List<String> = loadTestInput(DAY)

    with(input) {
        part1(this)
        part2(this)
    }
}

private fun part1(input: List<String>) {
    var res: Int
    for (str in input) {
        val charQueue = ArrayDeque<Char>()
        res = 0
        for (c in str) {
            if (charQueue.size < 4 || containsDuplicateChar(charQueue)) {
                charQueue.addLast(c)
                //begin to empty the queue
                if (charQueue.size > 4) {
                    charQueue.removeFirst()
                }
            } else {
                //sequence found
                break
            }
            res += 1
        }
        println("Day $DAY, Part 1: $res")
    }
}

private fun part2(input: List<String>) {
    var res: Int
    for (str in input) {
        val charQueue = ArrayDeque<Char>()
        res = 0
        for (c in str) {
            if (charQueue.size < 14 || containsDuplicateChar(charQueue)) {
                charQueue.addLast(c)
                //begin to empty the queue
                if (charQueue.size > 14) {
                    charQueue.removeFirst()
                }
            } else {
                //sequence found
                break
            }
            res += 1
        }
        println("Day $DAY, Part 2: $res")
    }
}

fun containsDuplicateChar(charQueue: ArrayDeque<Char>): Boolean {
    var res = false
    val temp = charQueue.toList().toMutableList()

    for (c in charQueue) {
        temp.remove(c)
        if (temp.contains(c)) {
            res = true
            break
        }
    }

    return res
}