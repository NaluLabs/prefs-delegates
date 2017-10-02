/*
 * Copyright 2017 Fabio Collini
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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