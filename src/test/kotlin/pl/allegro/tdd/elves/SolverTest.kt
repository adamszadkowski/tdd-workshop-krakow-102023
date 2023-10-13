package pl.allegro.tdd.elves

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class SolverTest {

    @Test
    fun `solve part 1`() {
        val provider = ResourceContentProvider("puzzle-input.txt")
        val counter = CaloriesCounter(provider)

        expectThat(counter.count().first()).isEqualTo(69177)
    }
}
