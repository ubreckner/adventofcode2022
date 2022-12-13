package days

private const val DAY = 7

private const val FILE_SYSTEM_SIZE = 70_000_000
private const val UPDATE_REQUIRED_SIZE = 30_000_000

fun main() {
    val input = loadInput(DAY)
    val testInput: List<String> = loadTestInput(DAY)


    val root = AoCFolder("/", null)
    var currentFolder: AoCFolder? = root

    //to more easily iterate over all folders at the end
    val folderList: MutableList<AoCFolder> = mutableListOf()
    folderList.add(root)

    for (line in input) {
        when {
            //cds
            line.startsWith("$ cd ") -> {
                val dest = line.substringAfter("$ cd ")
                currentFolder = when (dest) {
                    ".." -> {
                        currentFolder!!.parent
                    }

                    "/" -> {
                        root
                    }

                    else -> {
                        currentFolder!!.folders.first { it.name == dest }
                    }
                }
            }
            //ls dir
            line.startsWith("dir ") -> {
                val newFolder = AoCFolder(line.substringAfter("dir "), currentFolder)
                currentFolder!!.folders.add(newFolder)
                folderList.add(newFolder)
            }
            //ls file
            line.matches("^\\d+ \\D+".toRegex()) -> {
                currentFolder!!.files.add(AoCFolder.AoCFile(line.split(" ")[0].toInt(), line.split(" ")[1]))
            }

            else -> {//pff just ls command
            }
        }
    }

    with(folderList) {
        part1(this)
        part2(this, root)
    }
}

private fun part1(input: MutableList<AoCFolder>) {
    //filter for desired predicate
    val res = input.filter { it.getIncludedFilesSize() <= 100_000 }
    println("Day $DAY, Part 1: ${res.sumOf { it.getIncludedFilesSize() }}")
}

fun part2(input: MutableList<AoCFolder>, root: AoCFolder) {
    val curSystemSize = root.getIncludedFilesSize()
    val neededSpace = UPDATE_REQUIRED_SIZE - (FILE_SYSTEM_SIZE - curSystemSize)

    //filter for desired predicate
    val res = input.filter { it.getIncludedFilesSize() >= neededSpace }
    println("Day $DAY, Part 2: ${res.minOf { it.getIncludedFilesSize() }}")

}

class AoCFolder(val name: String, val parent: AoCFolder?) {
    val folders: MutableList<AoCFolder> = mutableListOf()
    val files: MutableList<AoCFile> = mutableListOf()

    fun getIncludedFilesSize(): Int {
        var res = 0

        res += files.sumOf { it.bytes }
        res += folders.sumOf { it.getIncludedFilesSize() }

        return res
    }

    class AoCFile(val bytes: Int, val name: String) {
    }
}