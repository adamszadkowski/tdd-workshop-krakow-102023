package pl.allegro.tdd.domain

import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import pl.allegro.tdd.domain.model.InvalidMessageException
import strikt.api.expectCatching
import strikt.api.expectThrows
import strikt.assertions.isSuccess

class GreetingServiceTest {
    private val service = GreetingService()

    @Nested
    inner class `validate length` {

        @ParameterizedTest
        @ValueSource(ints = [0, 100, 101])
        fun `fail to update`(length: Int) {
            expectThrows<InvalidMessageException> {
                service.updateGreeting("a".repeat(length))
            }
        }

        @ParameterizedTest
        @ValueSource(ints = [1, 2, 98, 99])
        fun `update message`(length: Int) {
            expectCatching {
                service.updateGreeting("a".repeat(length))
            }.isSuccess()
        }
    }
}
