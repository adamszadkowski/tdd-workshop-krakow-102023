package pl.allegro.tdd.api

import org.springframework.web.bind.annotation.*
import pl.allegro.tdd.domain.GreetingService
import pl.allegro.tdd.domain.model.Greeting

@RestController
@RequestMapping("/greeting")
class GreetingEndpoint(
    private val greetingService: GreetingService,
) {

    @GetMapping
    fun getGreeting(): GreetingResponse =
        greetingService.getGreeting().toResponse()

    @PutMapping
    fun updateGreeting(@RequestBody updateRequest: UpdateRequest): GreetingResponse =
        greetingService.updateGreeting(updateRequest.message).toResponse()

    private fun Greeting.toResponse() = GreetingResponse(message)
}

data class GreetingResponse(
    val message: String,
)

data class UpdateRequest(
    val message: String,
)
