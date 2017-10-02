package com.nalulabs.prefs

import com.nalulabs.prefs.fake.FakeSharedPreferences
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PrefsTest {

    val prefs = FakeSharedPreferences()

    var int by prefs.int()

    @Test
    fun writeInt() {
        int = 5

        assertThat(prefs.getInt("int", 0)).isEqualTo(5)
    }

    @Test
    fun readInt() {
        prefs.edit().putInt("int", 10).apply()

        assertThat(int).isEqualTo(10)
    }

    @Test
    fun incrementValue() {
        int = 5

        int++

        assertThat(prefs.getInt("int", 0)).isEqualTo(6)
    }
}