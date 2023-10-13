package pl.allegro.tdd.elves

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.containsExactly
import strikt.assertions.containsExactlyInAnyOrder
import strikt.assertions.isEmpty

class CaloriesCounterTest {
    private var content = ""
    private val counter = CaloriesCounter { content }

    @Test
    fun `empty calories`() {
        expectThat(counter.count()).isEmpty()
    }

    @Nested
    inner class `single elf` {

        @Test
        fun `single item`() {
            content = "1000"
            expectThat(counter.count()).containsExactly(1000)
        }

        @Test
        fun `two items`() {
            content = """
                1000
                2001
            """.trimIndent()
            expectThat(counter.count()).containsExactly(3001)
        }
    }

    @Nested
    inner class `two elves` {

        @Test
        fun `single item`() {
            content = """
                1000

                2000
            """.trimIndent()
            expectThat(counter.count()).containsExactlyInAnyOrder(1000, 2000)
        }

        @Test
        fun `two items`() {
            content = """
                1000
                300

                2000
                400
            """.trimIndent()
            expectThat(counter.count()).containsExactlyInAnyOrder(1300, 2400)
        }
    }

    @Nested
    inner class `ordering` {

        @Test
        fun `sort by calories`() {
            content = """
                1000

                2000
            """.trimIndent()
            expectThat(counter.count()).containsExactly(2000, 1000)
        }
    }
}
