package pl.allegro.tdd.domain

import pl.allegro.tdd.domain.model.Greeting
import java.util.concurrent.atomic.AtomicReference

class GreetingService {
    private val message = AtomicReference(Greeting("hello world"))

    fun getGreeting(): Greeting {
        return message.get()
    }

    fun updateGreeting(message: String): Greeting {
        return this.message.updateAndGet { Greeting(message) }
    }
}
