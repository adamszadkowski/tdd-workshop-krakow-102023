package pl.allegro.tdd.domain

import java.util.concurrent.atomic.AtomicReference

class GreetingService {
    private val message = AtomicReference("hello world")

    fun getGreeting(): String {
        return message.get()
    }

    fun updateGreeting(message: String): String {
        return this.message.updateAndGet { message }
    }
}
