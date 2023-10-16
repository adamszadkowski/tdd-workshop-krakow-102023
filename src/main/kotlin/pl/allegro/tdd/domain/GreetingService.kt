package pl.allegro.tdd.domain

import pl.allegro.tdd.domain.model.Greeting
import pl.allegro.tdd.domain.model.InvalidMessageException
import java.util.concurrent.atomic.AtomicReference

class GreetingService {
    private val message = AtomicReference(Greeting("hello world"))

    fun getGreeting(): Greeting {
        return message.get()
    }

    fun updateGreeting(message: String): Greeting {
        if (message.isEmpty()) throw InvalidMessageException()
        else if (message.length >= 100) throw InvalidMessageException()
        return this.message.updateAndGet { Greeting(message) }
    }
}
