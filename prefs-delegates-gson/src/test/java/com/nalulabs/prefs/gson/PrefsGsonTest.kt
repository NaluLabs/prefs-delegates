package com.nalulabs.prefs.gson

import com.nalulabs.prefs.fake.FakeSharedPreferences
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PrefsGsonTest {

    val prefs = FakeSharedPreferences()

    var person by prefs.json<Person?>(null)

    var personCached by prefs.json<Person?>(defaultValue = null, cacheValue = true)

    @Test
    fun saveValue() {
        person = Person("a", "b")

        assertThat(prefs.getString("person", "")).isEqualTo("""{"name":"a","surname":"b"}""")
    }

    @Test
    fun loadValue() {
        prefs.edit().putString("person", """{"name":"a","surname":"b"}""").apply()

        assertThat(person).isEqualTo(Person("a", "b"))
    }

    @Test
    fun cacheValue() {
        personCached = Person("a", "b")

        prefs.edit().putString("person", "abc").apply()

        assertThat(personCached).isEqualTo(Person("a", "b"))
    }
}