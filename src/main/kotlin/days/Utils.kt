package days

import java.io.File
import java.io.FileInputStream
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.LocalDate
import java.util.*

private fun downloadInput(day: Int) {
    val properties = Properties()
    val propFile = File("src/main/resources", "aoc.properties")
    FileInputStream(propFile).use { properties.load(it) }

    val client = HttpClient.newBuilder().build()
    val request = HttpRequest.newBuilder()
        .uri(URI.create("https://adventofcode.com/2022/day/$day/input"))
        .header(
            "cookie",
            properties.getProperty("my.personal.cookie")
        )
        .header("User-Agent", "github.com/ubreckner/adventofcode2022 Contact: Discord @v1per#2213")
        .build()

    val response = client.send(request, HttpResponse.BodyHandlers.ofString())
    File("src/main/resources", "Day$day.txt").bufferedWriter()
        .use { writer -> writer.write(response.body()) }
}

fun loadInput(day: Int): List<String> {
    val file = File("src/main/resources", "Day$day.txt")
    val date = LocalDate.now()
    var res = emptyList<String>()

    if (date.dayOfMonth >= day && !file.exists()) {
        downloadInput(day)
    }
    if (file.exists()) {
        res = file.readLines()
    }
    return res
}

fun loadTestInput(day: Int): List<String> {
    val file = File("src/main/resources", "Day$day-test.txt")
    if (!file.exists()) {
        return emptyList()
    }
    return file.readLines()
}
inline fun <reified T> transpose(inputList: List<List<T>>): List<List<T>> {
    val cols = inputList[0].size
    val rows = inputList.size
    return List(cols) { j ->
        List(rows) { i ->
            inputList[i][j]
        }
    }
}