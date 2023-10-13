package pl.allegro.tdd

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.doesNotContain
import strikt.assertions.isFalse
import strikt.assertions.isTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PasswordValidatorTest {

    @Test
    fun `all failures`() {
        expectThat(describePassword("")).contains(
            "too short",
            "no digits",
            "no big letter",
        )
    }

    @ParameterizedTest
    @MethodSource("getTooShort")
    fun `fail on too short`(length: Int) {
        expectThat(describePassword("a".repeat(length))).contains("too short")
    }

    val tooShort = (0..5).toList()

    @Test
    fun `pass on correct length`() {
        expectThat(describePassword("123456")).doesNotContain("too short")
    }

    @ParameterizedTest
    @MethodSource("getDigits")
    fun `pass having digit`(singleDigitsPassword: String) {
        expectThat(describePassword(singleDigitsPassword)).doesNotContain("no digits")
    }

    val digits = (0..9).map { it.toString() }.toList()

    @ParameterizedTest
    @MethodSource("getBigLetter")
    fun `pass having big letter`(password: String) {
        expectThat(describePassword(password)).doesNotContain("no big letter")
    }

    val bigLetter = ('A'..'Z').map { it.toString() }.toList()

    @Test
    fun `validate password`() {
        expectThat(validatePassword("123ABC")).isTrue()
        expectThat(validatePassword("")).isFalse()
    }

    private fun validatePassword(password: String): Boolean {
        return describePassword(password).isEmpty()
    }

    private fun describePassword(password: String): List<String> {
        val violations = mutableListOf<String>()
        if (!(password.length > 5)) violations += "too short"
        if (!password.contains("[0-9]".toRegex())) violations += "no digits"
        if (!password.contains("[A-Z]".toRegex())) violations += "no big letter"
        return violations
    }
}
