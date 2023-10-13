package pl.allegro.tdd.elves

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class ResourceContentProviderTest {
    private val provider: ContentProvider = ResourceContentProvider("sample.txt")

    @Test
    fun `read content`() {
        expectThat(provider.provide()).isEqualTo(
            """
                content of
                sample.txt
            """.trimIndent()
        )
    }
}
