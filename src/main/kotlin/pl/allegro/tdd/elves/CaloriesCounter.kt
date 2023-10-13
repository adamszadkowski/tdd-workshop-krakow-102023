package pl.allegro.tdd.elves

class CaloriesCounter(
    private val contentProvider: ContentProvider,
) {
    fun count(): List<Int> =
        contentProvider
            .provide()
            .split("\n\n")
            .filter { it.isNotEmpty() }
            .map { singleElf ->
                singleElf
                    .split("\n")
                    .sumOf { it.toIntOrNull() ?: 0 }
            }
            .sortedDescending()
}
