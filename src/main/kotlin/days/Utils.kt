package days

import java.io.File
import java.io.FileInputStream
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
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
        .build()

    val response = client.send(request, HttpResponse.BodyHandlers.ofString())
    File("src/main/resources", "Day$day.txt").bufferedWriter()
        .use { writer -> writer.write(response.body()) }
}

fun loadInput(day: Int): List<String> {
    val file = File("src/main/resources", "Day$day.txt")
    if (!file.exists()) {
        downloadInput(day)
    }
    return file.readLines()
}