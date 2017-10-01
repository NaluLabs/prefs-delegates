package com.nalulabs.prefs.demo

import com.nalulabs.prefs.fake.FakeSharedPreferences
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TokenHolderTest {
    @Test
    fun shouldCount() {
        val prefs = FakeSharedPreferences()
        val tokenHolder = TokenHolder(prefs)

        tokenHolder.saveToken("a")
        tokenHolder.saveToken("b")

        assertThat(tokenHolder.count).isEqualTo(2)
        assertThat(prefs.getInt("count", 0)).isEqualTo(2)
    }
}