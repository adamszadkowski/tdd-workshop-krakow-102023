package pl.allegro.tdd.domain

import pl.allegro.tdd.domain.model.Greeting
import pl.allegro.tdd.domain.model.InvalidMessageException
import java.util.concurrent.atomic.AtomicReference

class GreetingService {
    private val message = AtomicReference(Greeting("hello world"))

    fun getGreeting(): Greeting {
        return message.get()
    }

    fun updateGreeting(message: String): Greeting =
        when (message.length) {
            !in 1..<100 -> throw InvalidMessageException()
            else -> this.message.updateAndGet { Greeting(message) }
        }
}
